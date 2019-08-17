package com.qbhy.poster.kernal.upyun;

import com.UpYun;
import com.qbhy.poster.UpYunConfig;
import com.qbhy.poster.contracts.Result;
import com.qbhy.poster.contracts.Uploader;
import com.upyun.UpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    public Result upload(File file) throws IOException {
        String fileMd5 = DigestUtils.md5DigestAsHex(new FileInputStream(file));
        try {
            boolean flag = this.upyun.writeFile(fileMd5, file);

            if (flag) {
                UpYunUploadResult result = new UpYunUploadResult(Result.SUCCESSFUL, "OK");
                Map<String, String> data = new HashMap<String, String>(){};
                data.put("url", this.upYunConfig.url(fileMd5));
                result.setData(data);
                return result;
            }
            throw new IOException("文件上传失败");
        } catch (UpException e) {
            return null;
        }
    }
}
