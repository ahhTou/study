package com.ahhTou.servlet;

import com.ahhTou.utils.BgSearcher;
import com.ahhTou.utils.MyObjectMapper;
import com.ahhTou.utils.MyRedis;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class BgSearcherService {

    @Resource
    BgSearcher bgSearcher;

    @Resource
    MyRedis myRedis;

    @Resource
    MyObjectMapper myObjectMapper;

    @PostConstruct
    public void service() {
        bgSearcher.saveSetting(setting -> {

            if (setting == null) throw new RuntimeException("图片列表为空");

            else myRedis
                    .setForString("BgListToString-ahhTou", myObjectMapper.obj2String(bgSearcher.getProxyPath()))
                    .expire("BgListToString-ahhTou", 1, TimeUnit.DAYS);

        }).start();
    }
}
