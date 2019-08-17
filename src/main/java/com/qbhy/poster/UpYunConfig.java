package com.qbhy.poster;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(value = "upyun")
@Data
public class UpYunConfig {
    /**
     * 空间名
     */
    private String bucket;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 前缀
     */
    private String prefix;

    /**
     * domain
     */
    private String domain;

    public String url(String path) {
        return this.domain + this.prefix + path;
    }
}
