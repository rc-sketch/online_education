package com.oe.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @program: springboot_day03
 * @description:
 * @author: zh
 * @create: 2020-12-04 19:48
 */
@Configuration //当前类是配置类
public class DruidDataSources {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    //在spring中注册德鲁伊连接池对象,因为springboot对阿里的东西没有主动提供自动配置，所以需要我们手动注册
    public DataSource druid(){
        return new DruidDataSource();
    }
}