package com.cloud.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oss")
@Data
public class OSSConfig {

    private String endPoint;

    private String bucketName;
}
