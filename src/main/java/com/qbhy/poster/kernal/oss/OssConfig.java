package com.qbhy.poster.kernal.oss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(value = "oss")
public class OssConfig {
    private String endpoint;
    private String domain;
    private String bucket;
    private String secret;
    private String access;
    private String prefix;
}
