package com.qbhy.poster.kernal.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.qbhy.poster.contracts.Uploader;
import com.qbhy.poster.kernal.UploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Component
public class OssUploader implements Uploader {

    @Autowired
    OssConfig config;

    OSS ossClient;

    public OSS getOssClient() {
        return ossClient;
    }

    @Autowired
    public void setOssClient() {
        this.ossClient = new OSSClientBuilder().build(config.getEndpoint(), config.getAccess(), config.getSecret());
    }

    @Override
    public UploadResult upload(File file) throws IOException {
        String filepath = config.getPrefix() + "/" + DigestUtils.md5DigestAsHex(new FileInputStream(file));
        PutObjectResult result = ossClient.putObject(config.getBucket(), filepath, file);

        return new UploadResult(config.getDomain() + filepath);
    }
}
