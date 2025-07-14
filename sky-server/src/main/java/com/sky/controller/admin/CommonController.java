package com.sky.controller.admin;


import com.sky.result.Result;
import com.sky.utils.S3Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/admin/common")
@Api(tags = "Common api")
public class CommonController {

    @Autowired
    private S3Utils s3Utils;
    /**
     * Upload file
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("Upload document")
    public Result<String> upload(MultipartFile file) {
        log.info("Upload document: {}", file);

        try {
            String originalFilename = file.getOriginalFilename();

            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

            String newFileName = UUID.randomUUID().toString() + extension;

            String filePath = s3Utils.upload(file.getBytes(), newFileName);
            return  Result.success(filePath);
        } catch (IOException e) {
            log.error("Upload file error: ", e);
        }
        return Result.error("Upload file error");
    }

}
