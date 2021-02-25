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
 * @ProjectName: sprongcloud_test
 * @Package: com.yzh.config
 * @ClassName: Swagger2Config
 * @Author: Y眼中人Y
 * @Date: 2021/1/16 - 17:34
 * @Version: 1.0
 */
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
                .title("在线教育")
                // 大标题
                .description("学生端接口增删改查")
                //描述
                .termsOfServiceUrl("http://www.baidu.com")
                //网络服务地址
                .version("1.0.0")
                //版本号
                .build();
    }
}
