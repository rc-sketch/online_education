package com.oe.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Deacription TODO
 * @Author 石晗
 * @Date 2021/1/20 15:15
 * @Version 1.0  2222222
 **/
@Configuration
public class FeignConfig {
    // 眼中人
    @Bean
    public Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }
}
