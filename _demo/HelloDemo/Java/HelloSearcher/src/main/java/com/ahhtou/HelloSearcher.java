package com.ahhtou;

import com.ahhtou.handler.ArgsHandler;
import com.ahhtou.logger.HelloLogger;
import com.ahhtou.handler.DoHandler;
import com.ahhtou.service.DoStart;
import com.ahhtou.service.GetThings;
import com.ahhtou.handler.ReturnHandler;
import com.ahhtou.utils.HelloUtils;
import com.ahhtou.var.Properties;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Data
public class HelloSearcher {


    // name
    private String name;

    // 需要赋值的
    private Properties properties = null;
    private HelloLogger log = null;
    private HelloUtils helloUtils = null;

    // 储存url
    private Set<String> urls = new HashSet<>();

    // 控制启动
    private boolean isStart = false;

    private GetThings<Set<String>> getThings;

    /* Handler */
    private ArgsHandler<Set<String>> saveHandler = args -> log.logWhite("SaveHandler 并未执行");
    private ReturnHandler<Set<String>> searchHandler = () -> null;
    private DoHandler preHandler = () -> log.logWhite("PreHandler 并未执行");

    public HelloSearcher(GetThings<Set<String>> getThings) {
        this.getThings = getThings;
    }

    /* 创建一个Searcher */
    public static HelloSearcher create(GetThings<Set<String>> getThings) {
        HelloSearcher helloSearcher = new HelloSearcher(getThings);
        HelloLogger hello = getThings.getHelloLogger();
        helloSearcher.setLog(hello);
        helloSearcher.setProperties(getThings.getProperties());
        helloSearcher.setHelloUtils(getThings.getHelloUtils());

        hello.logPurple("实例被创建 √");
        return helloSearcher;
    }

    @SneakyThrows
    public void start() {
        isStart = true;
        while (isStart) {
            // 运行前执行
            preHandler.handler();

            // 搜寻
            log.log("实例进行了一次搜寻");
            Set<String> result = searchHandler.handler();
            log.log("实例结束了一次搜寻");

            // 保存 后 清除
            urls = result;
            saveHandler.handler(urls);
            urls.clear();

            // 休眠
            if (this.properties.getCycleTime() == 0) {
                isStart = false;
                log.log("未设置循环时间, 该实例将结束搜寻");
            } else TimeUnit.MINUTES.sleep(this.properties.getCycleTime());
        }
        log.logGB("已结束搜寻，该实例已结束任务");
    }


    public void shutdown() {
        isStart = false;
    }


    public HelloSearcher setSaveHandler(ArgsHandler<Set<String>> saveHandler) {
        this.saveHandler = saveHandler;
        return this;
    }

    public HelloSearcher setPreHandler(DoHandler bfFuc) {
        preHandler = bfFuc;
        return this;
    }

    public HelloSearcher setSearchHandler(ReturnHandler<Set<String>> handler) {
        searchHandler = handler;
        return this;
    }
}
