package com.sky.config;

import com.sky.properties.AWSProperties;
import com.sky.utils.S3Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class S3Configuration {

    @Bean
    @ConditionalOnMissingBean
    public S3Utils s3Utils(AWSProperties awsProperties) {
        log.info("开始创建AWS S3 上传工具类对象: {}", awsProperties);
        S3Utils s3Utils = new S3Utils(awsProperties.getRegion(),
                awsProperties.getAccessKey(),
                awsProperties.getAccessSecret(),
                awsProperties.getBucketName());
        return s3Utils;
    }
}
