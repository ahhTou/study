package com.ahhtou.servlet;

import com.ahhtou.filter.MyFilter;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.util.Arrays;


@Configuration
public class MyServerConfig {

    // 注册三大组件

    //servlet
    @Bean
    public ServletRegistrationBean<Servlet> myServlet() {
        System.out.println("...MyServlet");
        ServletRegistrationBean<Servlet> registrationBean =
                new ServletRegistrationBean<>(new MyServlet(), "/servlet1", "/servlet2");
        registrationBean.addUrlMappings("/hello");
        System.out.println(registrationBean);
        return registrationBean;
    }

    //filter
    @Bean
    public FilterRegistrationBean<Filter> myFilter() {
        System.out.println("...MyFilter");
        FilterRegistrationBean<Filter> filterBean = new FilterRegistrationBean<>();
        filterBean.setFilter(new MyFilter());
        filterBean.setUrlPatterns(Arrays.asList("/hello", "/filter1"));
        System.out.println(filterBean);
        return filterBean;
    }

    // 配置嵌入式的servlet容器
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.setPort(8082);
            }
        };
    }
}
