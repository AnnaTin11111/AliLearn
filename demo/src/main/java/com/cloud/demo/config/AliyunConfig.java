package com.cloud.demo.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aliyun")
@Data
public class AliyunConfig {

    private String accessId;

    private String accessKey;
}
