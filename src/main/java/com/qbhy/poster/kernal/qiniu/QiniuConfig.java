package com.qbhy.poster.kernal.qiniu;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(value = "qiniu")
public class QiniuConfig {
    private String domain;
    private String bucket;
    private String access;
    private String secret;
    private String prefix;
}
