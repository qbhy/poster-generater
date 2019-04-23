package com.qbhy.poster.kernal.Smms;

import com.qbhy.poster.contracts.JsonableInterface;
import com.qbhy.poster.contracts.UploaderInterface;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class SmmsUploader implements UploaderInterface {

    public static final MediaType IMAGE = MediaType.parse("application/octet-stream");

    public static OkHttpClient client = new OkHttpClient();

    public static SmmsUploadResult upload(File file) throws IOException {

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("smfile", file.getName(), RequestBody.create(IMAGE, file))
                .build();

        Request request = new Request.Builder()
                .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
                .addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
                .url("https://sm.ms/api/upload")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return (SmmsUploadResult)JsonableInterface.decode(response.body().string(), SmmsUploadResult.class);
        }
    }
}
