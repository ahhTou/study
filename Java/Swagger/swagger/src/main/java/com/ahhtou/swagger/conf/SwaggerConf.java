package com.ahhtou.swagger.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2//开启Swagger 2
public class SwaggerConf {

    // 配置了Swagger 的 Docket 的 bean实例
    @Bean
    public Docket docket1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Hello");
    }

    @Bean
    public Docket docket2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("HelloPlus")
                .select()
                // 配置要扫描接口的方式
                // any() 扫描全部
                // none() 不扫描
                // withClassAnnotation()
                // withMethodAnnotation()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.ant("/ahhTou/**"))
                .build();
    }

    // 配置Swagger 信息 = apiInfo
    private ApiInfo apiInfo() {
        return new ApiInfo("ahhTou Api 接口文档",
                "阿偷",
                "v1.0",
                "null",
                new Contact("ahhTou", "", ""),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }
}
