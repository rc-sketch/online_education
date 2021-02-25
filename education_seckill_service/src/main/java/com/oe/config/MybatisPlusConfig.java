package com.oe.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Deacription TODO
 * @Author 石晗
 * @Date 2021/1/9 11:45
 * @Version 1.0
 **/
@Configuration
public class MybatisPlusConfig {
    @Bean//注入到spring容器中  PaginationInterceptor:分页插件类
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

}
