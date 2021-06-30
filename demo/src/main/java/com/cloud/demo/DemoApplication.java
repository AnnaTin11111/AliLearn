package com.cloud.demo;

import com.cloud.demo.config.AliyunConfig;
import com.cloud.demo.config.OSSConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({AliyunConfig.class, OSSConfig.class})
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
