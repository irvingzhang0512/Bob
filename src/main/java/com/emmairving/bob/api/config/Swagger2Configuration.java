package com.emmairving.bob.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by irving on 17/2/3.
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {
    @Bean
    public Docket customDocket(){
        Contact contact = new Contact("irving","emmairving.com","irvingzhang0512@gmail.com");
        ApiInfo apiInfo = new ApiInfo(
                "基于无线网的分布式能量管理系统", //项目标题
                "研究生毕业设计", //项目描述
                "1.0.0", //版本
                null, //应用url
                contact, //作者名称, 网页, 联系方式
                null, //许可
                null); //许可url
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo);
    }
}