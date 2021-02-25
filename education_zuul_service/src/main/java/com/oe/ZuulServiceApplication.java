package com.oe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Deacription TODO
 * @Author 石晗
 * @Date 2021/1/29 10:53
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulServiceApplication.class,args);
    }
}
