package com.sky.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.net.URI;

@Data
@AllArgsConstructor
@Slf4j
public class S3Utils {

    private String region;
    private String accessKey;
    private String accessSecret;
    private String bucketName;

    /**
     * 上传文件到 AWS S3
     *
     * @param bytes      文件内容（字节数组）
     * @param objectName 文件名（含路径），如：images/test.jpg
     * @return 公网访问路径
     */
    public String upload(byte[] bytes, String objectName) {
        S3Client s3Client = null;

        try {

            S3ClientBuilder builder = S3Client.builder()
                    .region(Region.of(region))
                    .credentialsProvider(
                            StaticCredentialsProvider.create(
                                    AwsBasicCredentials.create(accessKey, accessSecret)
                            )
                    );

            s3Client = builder.build();

            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(objectName)
                    .contentType("image/png")
                    .build();

            s3Client.putObject(request, RequestBody.fromBytes(bytes));
        } catch (Exception e) {
            log.error("上传到 S3 失败: {}", e.getMessage(), e);
            throw new RuntimeException("上传到 S3 失败", e);
        } finally {
            if (s3Client != null) {
                s3Client.close();
            }
        }

        String fileUrl;
        fileUrl = String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, objectName);


        log.info("文件上传成功，访问地址: {}", fileUrl);
        return fileUrl;
    }
}

