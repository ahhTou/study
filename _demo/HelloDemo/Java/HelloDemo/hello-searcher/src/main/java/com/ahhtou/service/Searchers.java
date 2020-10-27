package com.ahhtou.service;

import com.ahhtou.common.pojo.BgList;
import com.ahhtou.doSome.GetThings;
import com.ahhtou.common.handler.ArgsHandler;
import com.ahhtou.common.handler.DoHandler;
import com.ahhtou.common.handler.ReturnHandler;
import com.ahhtou.logger.HelloLogger;
import com.ahhtou.nosql.service.SearchRedis;
import com.ahhtou.utils.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

@Component
public class Searchers {

    @Resource(name = "aLogger")
    HelloLogger searchLogger;

    @Resource(name = "sThings")
    GetThings<Set<String>> sThings;

    @Resource(name = "zacThings")
    GetThings<Set<String>> zacThings;

    @Resource
    SearchRedis searchRedis;

    @Bean
    HelloSearcher primeSearcher() {
        // preHandler
        DoHandler preHandler = () -> {
            searchLogger.logWhite("PreHandler正在执行");
            zacThings.doSearch().doFilter().doTrans()
                    .doZip().doCut().doAddSuffix().doWrite();
            searchLogger.logWhite("PreHandler执行结束");
        };

        // searcherHandler
        ReturnHandler<Set<String>> searcherHandler = () -> sThings.doSearch().getResult();


        // saveHandler
        ArgsHandler<Set<String>> saveHandler = args -> {
            searchLogger.logWhite("saveHandler正在执行");


            Properties properties = sThings.getProperties();
            Integer h = properties.getHeight();
            Integer w = properties.getWidth();
            Set<String> set = properties.getPath();
            String proxy = properties.getProxyPath();
            BgList bgList = new BgList();
            bgList.setHeight(h);
            bgList.setWidth(w);
            bgList.setProxyUrl(proxy);
            bgList.setBgList(args);
            bgList.setSearchPaths(set);

            searchRedis.saveBgList(bgList);
            searchLogger.logWhite("saveHandler执行结束");
        };

        return HelloSearcher
                .create(sThings)
                .setPreHandler(preHandler)
                .setSearchHandler(searcherHandler)
                .setSaveHandler(saveHandler);


    }

}
