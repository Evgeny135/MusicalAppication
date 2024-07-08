package org.application.musicalappication.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@Service
public class StorageService {

    private final S3Client s3Client;

    @Autowired
    public StorageService(S3Client s3Client) {
        this.s3Client = s3Client;
    }


    public String uploadFile(String bucketName, MultipartFile file){
        String key = file.getOriginalFilename();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        PutObjectResponse response = null;

        try ( InputStream inputStream = file.getInputStream()){

        response = s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, file.getSize()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return response.eTag();
    }
}
