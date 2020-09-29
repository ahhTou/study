package com.ahhtou.config;

import com.ahhtou.interceptor.TestInterceptor;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 使用webMvcConfigurer 扩展mvc功能
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TestInterceptor())
                .addPathPatterns("/*")
                .excludePathPatterns("/notFilter/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 浏览器发送 /ahhTou 请求来到
        registry
                .addViewController("/ahh")
                .setViewName("success");
    }


    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry
                        .addViewController("/ahh2")
                        .setViewName("success");
            }

        };
    }
}
