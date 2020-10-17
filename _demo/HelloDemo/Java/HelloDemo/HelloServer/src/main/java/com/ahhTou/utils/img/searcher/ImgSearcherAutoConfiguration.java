package com.ahhtou.utils.img.searcher;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@EnableConfigurationProperties({ImgSearcherProperties.class})
@ConditionalOnClass(ImgSearcher.class)
public class ImgSearcherAutoConfiguration {

    private final ImgSearcherProperties bgSearcherProperties;

    public ImgSearcherAutoConfiguration(ImgSearcherProperties bgSearcherProperties) {
        this.bgSearcherProperties = bgSearcherProperties;
    }


    @Bean
    @ConditionalOnMissingBean
    public ImgSearcher bgSearcher() {
        ImgSearcher imgSearcher = new ImgSearcher();


        imgSearcher.setProperties(bgSearcherProperties);

        return imgSearcher;
    }


}
