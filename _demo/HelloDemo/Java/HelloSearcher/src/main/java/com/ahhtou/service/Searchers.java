package com.ahhtou.service;

import com.ahhtou.doSome.DoSomeImpl.DoStart;
import com.ahhtou.doSome.DoSomeImpl.GetThings;
import com.ahhtou.handler.ArgsHandler;
import com.ahhtou.handler.DoHandler;
import com.ahhtou.handler.ReturnHandler;
import com.ahhtou.logger.HelloLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

@Component
public class Searchers {
    @Resource
    DoStart zacStater;

    @Resource
    DoStart searchStater;

    @Resource(name = "aLogger")
    HelloLogger searchLogger;

    @Resource(name = "sThings")
    GetThings<Set<String>> things;

    HelloLogger logger = new HelloLogger("Starter", "SPRING启动时执行");

    @Bean
    HelloSearcher primeSearcher() {
        // preHandler
        DoHandler preHandler = () -> {
            searchLogger.logWhite("PreHandler正在执行");
            zacStater.doSearch().doFilter().doTrans()
                    .doZip().doCut().doAddSuffix().doWrite();
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

        return HelloSearcher
                .create(things)
                .setPreHandler(preHandler)
                .setSearchHandler(searcherHandler)
                .setSaveHandler(saveHandler);


    }

}
