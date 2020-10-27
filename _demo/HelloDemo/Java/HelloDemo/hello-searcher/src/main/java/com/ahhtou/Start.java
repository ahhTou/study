package com.ahhtou;

import com.ahhtou.logger.HelloLogger;
import com.ahhtou.service.HelloSearcher;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


@Configuration
public class Start {
    HelloLogger logger = new HelloLogger("Starter", "SPRING启动时执行");

    @Resource
    HelloSearcher primeSearcher;


    @PostConstruct
    public void start() {
        logger.logGreen("正在执行启动Handler");

        primeSearcher.start();

        logger.logGreen("Handler运行结束");
    }
}
