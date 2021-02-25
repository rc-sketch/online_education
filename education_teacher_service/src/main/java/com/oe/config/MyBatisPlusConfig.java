package com.oe.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: online_education
 * @Package: com.oe.config
 * @ClassName: MyBatisPlusConfig
 * @Author: Y眼中人Y
 * @Date: 2021/1/30 - 17:35
 * @Version: 1.0
 */
@Configuration
public class MyBatisPlusConfig {
    // Mybatis plus 分页配置类
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }

}
