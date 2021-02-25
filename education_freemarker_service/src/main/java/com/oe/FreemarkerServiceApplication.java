package com.oe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description
 * @ClassName FreemarkerServiceApplication
 * @Author RC
 * @date 2021.02.01 15:05
 */
@SpringBootApplication//springboot
@EnableDiscoveryClient//注册中
@EnableFeignClients //开启fegin
@EnableCircuitBreaker //开启断路器功能
public class FreemarkerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FreemarkerServiceApplication.class,args);
    }
}
