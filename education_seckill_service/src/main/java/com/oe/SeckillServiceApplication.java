package com.oe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Deacription TODO
 * @Author 石晗
 * @Date 2021/2/1 16:04
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients     //开启fegin
@EnableSwagger2         //swagger注解
@EnableCircuitBreaker   //开启断路器功能
@MapperScan("com.oe.mapper")
@EnableScheduling//开始本项目的定时任务
public class SeckillServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeckillServiceApplication.class,args);
    }
}
