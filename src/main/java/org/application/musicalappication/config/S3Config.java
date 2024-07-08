package org.application.musicalappication.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;

@Configuration
public class S3Config {

    @Bean
    public S3Client s3Client(){
        return S3Client.builder()
                .region(Region.of("ru-central1-a"))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("YCAJEnTJOnfD4Gl4x0wJSH7C_","YCP9qVfLDv8rKf82P0aJjObbt4hR3yCE_S5Do550")
                ))
                .serviceConfiguration(S3Configuration.builder()
                        .pathStyleAccessEnabled(true)
                        .build())
                .endpointOverride(URI.create("https://storage.yandexcloud.net"))
                .build();
    }
}
