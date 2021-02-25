package com.oe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ProjectName: springcloud
 * @Package: com.oe
 * @ClassName: OrderServiceApplication
 * @Author: Y眼中人Y
 * @Date: 2021/2/2 - 10:51
 * @Version: 1.0
 */
@SpringBootApplication
@EnableSwagger2
//让注册中心能够发现，扫描到该服务。 所有注册中心都能找到
//@EnableEurekaClient只适用于Eureka作为注册中心，@EnableDiscoveryClient 可以是其他注册中心。
//@EnableDiscoveryClient
@EnableFeignClients//需要声明式feign的客户端
@EnableDiscoveryClient
@EnableCircuitBreaker
//或者使用@EnableHystrix 开启熔断功能
@MapperScan("com.oe.mapper")
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class,args);
    }
}
