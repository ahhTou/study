package com.ahhTou.conf;

import com.ahhTou.service.BaseService;
import com.ahhTou.utils.img.ImgSearcher;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
public class StarterConf {

    @Resource
    ImgSearcher imgSearcher;

    @Resource
    BaseService baseService;

    @PostConstruct
    public void start() {

        imgSearcher
                .create()
                .setPath("C:\\Users\\11979\\OneDrive\\图片")
                .setCycle(10)
                .saveFunc(urls -> {
                            baseService.saveBgImages(urls);
                        }
                )
                .start();

    }

}
