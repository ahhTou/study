package com.ahhtou.view_resolver;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;

public class MyViewResolver implements ViewResolver {

    @Bean
    public ViewResolver success() {
        return new MyViewResolver();
    }


    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
        return null;
    }

}
