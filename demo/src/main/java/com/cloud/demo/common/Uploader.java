package com.cloud.demo.common;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.cloud.demo.config.AliyunConfig;
import com.cloud.demo.config.OSSConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Slf4j
@Component
public class Uploader {

    @Autowired
    private OSSConfig ossConfig;

    @Autowired
    private AliyunConfig aliyunConfig;

    private OSS ossClient;

    private String host;

    @PostConstruct
    protected void init() {
        this.ossClient = new OSSClientBuilder()
                .build(ossConfig.getEndPoint(), aliyunConfig.getAccessId(), aliyunConfig.getAccessKey());
        this.host = "https://" + ossConfig.getBucketName() + "."  + ossConfig.getEndPoint() + "/";
    }

    public String put(String objectName, InputStream inputStream, String contentType) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        // 指定x-oss-forbid-overwrite为true时，表示禁止覆盖同名Object，如果同名Object已存在，程序将报错。
        objectMetadata.setHeader("x-oss-forbid-overwrite", "true");

        try {
            ossClient.putObject(ossConfig.getBucketName(), objectName, inputStream, objectMetadata);
        } catch (Exception e) {
            return null;
        }
        return host + objectName;
    }

    public InputStream get(String objectName) {
        OSSObject object = ossClient.getObject(ossConfig.getBucketName(), objectName);
        return object.getObjectContent();
    }

    public void delete(String objectName) {
        ossClient.deleteObject(ossConfig.getBucketName(), objectName);
    }
}
