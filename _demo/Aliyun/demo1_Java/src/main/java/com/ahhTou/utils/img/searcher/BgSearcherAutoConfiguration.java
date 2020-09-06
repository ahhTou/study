package com.ahhTou.utils.img.searcher;

import com.ahhTou.utils.img.ImgSearcher;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@EnableConfigurationProperties({BgSearcherProperties.class})
@ConditionalOnClass(ImgSearcher.class)
public class BgSearcherAutoConfiguration {

    private final BgSearcherProperties bgSearcherProperties;

    public BgSearcherAutoConfiguration(BgSearcherProperties bgSearcherProperties) {
        this.bgSearcherProperties = bgSearcherProperties;
    }


    @Bean
    @ConditionalOnMissingBean
    public ImgSearcher bgSearcher() {
        ImgSearcher imgSearcher = new ImgSearcher();

        System.out.println(bgSearcherProperties);

        imgSearcher.setProperties(bgSearcherProperties);

        return imgSearcher;
    }


}
