package com.oe.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * @Description
 * @ClassName FreeMarkerConfigurer
 * @Author RC
 * @date 2020.12.17 14:51
 */
@Configuration
public class FreeMarkerConfig {

    @Value("${freemarker.templateLoaderPath}")
    private String templateLoaderPath;
    @Value("${freemarker.defaultEncoding}")
    private String defaultEncoding;

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(){
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        //配置模板所在文件夹位置
        configurer.setTemplateLoaderPath(templateLoaderPath);
        //设置编码集格式
        configurer.setDefaultEncoding(defaultEncoding);
        return configurer;
    }
}
