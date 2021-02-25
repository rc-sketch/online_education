package com.oe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Deacription TODO
 * @Author 石晗
 * @Date 2021/2/1 22:00
 * @Version 1.0
 **/
@Configuration
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.oe.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("es搜索微服务接口文档")  // 大标题
                .description("es搜索接口,这个接口有一些关于搜索的增删改查.....") //描述
                .termsOfServiceUrl("http://www.ddit.com")  //网络服务地址
                .version("1.0.0")     //版本号
                .build();
    }
}
