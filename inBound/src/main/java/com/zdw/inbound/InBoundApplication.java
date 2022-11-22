package com.zdw.inbound;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableTransactionManagement
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@MapperScan(basePackages = "com.zdw.inbound.mapper")
public class InBoundApplication {

    public static void main(String[] args) {
        SpringApplication.run(InBoundApplication.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 模拟请求
     */
    @RestController
    class EchoController {
        @GetMapping(value = "/test/{string}")
        public String test(@PathVariable String string) {
            return "Hello Nacos Discovery " + string;
        }
    }

}
