//package com.zdw.inbound.config;
//
//import io.minio.MinioClient;
//import io.minio.errors.InvalidEndpointException;
//import io.minio.errors.InvalidPortException;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * minio配置类
// * @author 卓德文
// * @since 2022-11-11
// */
//@Configuration
//public class MinioConfig {
//
//    @Value("${minio.endPoint}")
//    private String endPoint;
//
//    @Value("${minio.accessKey}")
//    private String accessKey;
//
//    @Value("${minio.secretKey}")
//    private String secretKey;
//
//    @Bean
//    public MinioClient getMinioClient() throws InvalidPortException, InvalidEndpointException {
//        MinioClient minioClient = new MinioClient(endPoint,accessKey,secretKey);
//        return  minioClient;
//    }
//
//}
