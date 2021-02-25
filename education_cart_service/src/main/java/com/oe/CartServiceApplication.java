package com.oe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description
 * @ClassName CartServiceApplication
 * @Author RC
 * @date 2021.02.02 14:40
 */
@SpringBootApplication//springboot
@EnableDiscoveryClient//注册中
@EnableFeignClients //开启fegin
@EnableCircuitBreaker //开启断路器功能
@EnableSwagger2
public class CartServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartServiceApplication.class,args);
    }
}
