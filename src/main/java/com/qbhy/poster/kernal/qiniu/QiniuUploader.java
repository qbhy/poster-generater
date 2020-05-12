package com.qbhy.poster.kernal.qiniu;

import com.qbhy.poster.contracts.Uploader;
import com.qbhy.poster.kernal.UploadResult;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Component
public class QiniuUploader implements Uploader {

    @Autowired
    QiniuConfig config;

    UploadManager uploadManager = new UploadManager(new Configuration());

    Auth auth;

    @Autowired
    public void setAuth() {
        this.auth = Auth.create(config.getAccess(), config.getSecret());
    }

    public Auth getAuth() {
        return auth;
    }

    public UploadManager getUploadManager() {
        return uploadManager;
    }

    @Override
    public UploadResult upload(File file) throws IOException {
        String filepath = config.getPrefix() + "/" + DigestUtils.md5DigestAsHex(new FileInputStream(file));
        Response response = uploadManager.put(file, filepath, auth.uploadToken(config.getBucket()));

        if (response.isServerError() || !response.isOK()) {
            return UploadResult.fail(response.error);
        }

        return new UploadResult(config.getDomain() + filepath);
    }
}
