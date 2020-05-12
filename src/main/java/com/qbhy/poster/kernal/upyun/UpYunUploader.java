package com.qbhy.poster.kernal.upyun;

import com.UpYun;
import com.qbhy.poster.contracts.Uploader;
import com.qbhy.poster.kernal.UploadResult;
import com.upyun.UpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Component
public class UpYunUploader implements Uploader {

    private UpYun upyun;

    @Autowired
    private UpYunConfig upYunConfig;

    @Autowired
    public void setUpYun() {
        this.upyun = new UpYun(upYunConfig.getBucket(), upYunConfig.getUsername(), upYunConfig.getPassword());
        this.upyun.setApiDomain(UpYun.ED_AUTO); // 寻找最优节点
    }

    @Override
    public UploadResult upload(File file) throws IOException {
        String filepath = this.upYunConfig.getPrefix() + "/" + DigestUtils.md5DigestAsHex(new FileInputStream(file));
        try {
            boolean flag = this.upyun.writeFile(filepath, file);
            if (flag) {
                return new UploadResult(this.upYunConfig.url(filepath));
            }
            throw new IOException("文件上传失败");
        } catch (UpException e) {
            throw new IOException("文件上传失败:" + e.getMessage());
        }
    }
}
