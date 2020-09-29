package com.ahhTou.utils.img.zipper;

import com.ahhTou.utils.img.searcher.ImgSearcher;
import com.ahhTou.utils.img.searcher.ImgSearcherProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@EnableConfigurationProperties({ImgZipperProperties.class})
@ConditionalOnClass(ImgZipper.class)
public class ImgZipperAutoConfiguration {

    private final ImgZipperProperties imgZipperProperties;

    public ImgZipperAutoConfiguration(ImgZipperProperties imgZipperProperties) {
        this.imgZipperProperties = imgZipperProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public ImgZipper ImgZipper() {
        ImgZipper imgZipper = new ImgZipper();

        imgZipper.setImgZipperProperties(imgZipperProperties);

        return imgZipper;
    }
}
