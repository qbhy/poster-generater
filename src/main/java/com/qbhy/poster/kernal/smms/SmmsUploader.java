package com.qbhy.poster.kernal.smms;

import com.qbhy.poster.contracts.Jsonable;
import com.qbhy.poster.contracts.Result;
import com.qbhy.poster.contracts.Uploader;
import com.qbhy.poster.kernal.UploadResult;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Component
public class SmmsUploader implements Uploader {

    public static final MediaType IMAGE = MediaType.parse("application/octet-stream");

    public static OkHttpClient client = new OkHttpClient();

    public static Result push(File file) throws IOException {
        return (new SmmsUploader()).upload(file);
    }

    public UploadResult upload(File file) throws IOException {

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("smfile", file.getName(), RequestBody.create(IMAGE, file))
                .build();

        Request request = new Request.Builder()
                .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
                .addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
                .url("https://sm.ms/api/v2/upload")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        Jsonable.MapResult result = Jsonable.decode(response.body().string());
        if ((boolean) result.get("success")) {
            return new UploadResult(((Map<String, String>) result.get("data")).get("url"));
        }
        return UploadResult.fail((String) result.get("message"));
    }
}
