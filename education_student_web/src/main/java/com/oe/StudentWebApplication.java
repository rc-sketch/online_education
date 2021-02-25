package com.oe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: online_education
 * @description:
 * @author: 邢孟君
 * @create: 2021-02-01 11:44
 **/
@SpringBootApplication//springboot
@EnableDiscoveryClient//注册中
@EnableSwagger2 //swagger注解
@EnableFeignClients //开启fegin
@EnableCircuitBreaker //开启断路器功能

public class StudentWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentWebApplication.class, args);
    }
}

    