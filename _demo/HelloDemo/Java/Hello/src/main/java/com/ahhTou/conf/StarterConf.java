package com.ahhTou.conf;

import com.ahhTou.service.BaseService;
import com.ahhTou.utils.var.CommonVariable;
import com.ahhTou.utils.img.searcher.ImgSearcher;
import com.ahhTou.utils.img.zipper.ImgZipper;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
public class StarterConf {

    @Resource
    ImgSearcher imgSearcher;

    @Resource
    BaseService baseService;

    @Resource
    CommonVariable var;

    @Resource
    ImgZipper imgZipper;

    @PostConstruct
    public void start() {

        ImgZipper zipper = this.imgZipper
                .setSize(var.getSize())
                .setPath(var.getBgSearchPath());


        imgSearcher
                .create()
                .setBeforeStart(zipper::start)
                .setPath(var.getBgSearchPath())
                .setCycle(10)
                .setSaveFunc(baseService::saveBgImages)
                .start();

    }

}
