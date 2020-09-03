package com.ahhTou.utils.conf;

import com.ahhTou.utils.img.BgSearcher;
import com.ahhTou.utils.properties.BgSearcherProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@EnableConfigurationProperties({BgSearcherProperties.class})
@ConditionalOnClass(BgSearcher.class)
public class BgSearcherAutoConfiguration {

    private final BgSearcherProperties bgSearcherProperties;

    public BgSearcherAutoConfiguration(BgSearcherProperties bgSearcherProperties) {
        System.out.println("初始化");
        this.bgSearcherProperties = bgSearcherProperties;
    }


    @Bean
    @ConditionalOnMissingBean
    public BgSearcher bgSearcher() {
        System.out.println("123");
        BgSearcher bgSearcher = new BgSearcher();
        bgSearcher.setLocalUrl(bgSearcherProperties.getLocalUrl());
        bgSearcher.setProxyUrl(bgSearcherProperties.getProxyUrl());
        bgSearcher.setCycleTime(bgSearcherProperties.getCycleTime());
        bgSearcher.setImgRegs(bgSearcherProperties.getImgRegs());

        return bgSearcher;
    }


}
