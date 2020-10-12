package com.ahhtou;

import com.ahhtou.handler.ArgsHandler;
import com.ahhtou.logger.HelloLogger;
import com.ahhtou.handler.DoHandler;
import com.ahhtou.service.DoStart;
import com.ahhtou.handler.ReturnHandler;

import com.ahhtou.service.GetThings;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Set;


@Configuration
public class Start {

    @Resource
    DoStart zacStater;

    @Resource
    DoStart searchStater;

    @Resource(name = "aLogger")
    HelloLogger searchLogger;

    @Resource(name = "sThings")
    GetThings<Set<String>> things;

    HelloLogger logger = new HelloLogger("Starter", "SPRING启动时执行");


    @PostConstruct
    public void start() {
        logger.logGreen("正在执行启动Handler");


        // preHandler
        DoHandler preHandler = () -> {
            searchLogger.logWhite("PreHandler正在执行");
            zacStater.doSearch().doFilter().doTrans().doZip().doCut().doAddSuffix().doWrite();
            searchLogger.logWhite("PreHandler执行结束");
        };

        // searcherHandler
        ReturnHandler<Set<String>> searcherHandler = () -> searchStater.doSearch().getResult();

        // saveHandler
        ArgsHandler<Set<String>> saveHandler = args -> {
            searchLogger.logWhite("saveHandler正在执行");
            searchLogger.logRed("我的内容 -> " + args);
            searchLogger.logWhite("saveHandler执行结束");
        };

        HelloSearcher
                .create(things)
                .setPreHandler(preHandler)
                .setSearchHandler(searcherHandler)
                .setSaveHandler(saveHandler)
                .start();

        logger.logGreen("Handler运行结束");
    }
}
