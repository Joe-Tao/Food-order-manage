package com.sky.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sky.aws")
@Data
public class AWSProperties {
    private String region;
    private String accessKey;
    private String accessSecret;
    private String bucketName;
}
