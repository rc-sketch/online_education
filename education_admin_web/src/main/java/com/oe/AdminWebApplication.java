package com.oe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: online_education
 * @description:
 * @author: 邢孟君
 * @create: 2021-01-30 15:57
 **/
@SpringBootApplication//springboot
@EnableDiscoveryClient//注册中
@EnableSwagger2 //swagger注解
@EnableFeignClients //开启fegin
@EnableCircuitBreaker //开启断路器功能
public class AdminWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminWebApplication.class, args);
    }
}

    