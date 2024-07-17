package org.application.musicalappication.config;


import org.springframework.beans.factory.annotation.Value;
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

    @Value("${aws.api.key}")
    private String api;
    @Value("${aws.secret.key}")
    private  String secret;

    @Bean
    public S3Client s3Client(){
        return S3Client.builder()
                .region(Region.of("ru-central1-a"))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(api,secret)
                ))
                .serviceConfiguration(S3Configuration.builder()
                        .pathStyleAccessEnabled(true)
                        .build())
                .endpointOverride(URI.create("https://storage.yandexcloud.net"))
                .build();
    }
}
