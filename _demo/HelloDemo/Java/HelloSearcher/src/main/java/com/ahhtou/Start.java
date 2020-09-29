package com.ahhtou;

import com.ahhtou.search.ImgSearcher;
import com.ahhtou.var.Properties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


@Configuration
public class Start {

    @Resource
    Properties properties;


    @PostConstruct
    public void start() {

        ImgSearcher
                .create("搜索背景图片")
                .setPaths(properties.getPath())
                .setSuffix(properties.getImgSuffixReg())
                .setProxyUrl("123");
        ;

    }
}
