package com.ahhtou.conf;

import com.ahhtou.logger.HelloLogger;
import com.ahhtou.doSome.DoSomeImpl.DoStart;
import com.ahhtou.doSome.DoSomeImpl.GetThings;
import com.ahhtou.utils.Properties;
import com.ahhtou.utils.ZipAndCutProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class Conf {

    @Bean("aLogger")
    public HelloLogger searchLogger() {
        return new HelloLogger("Searcher", "背景图片查询");
    }

    @Bean
    public DoStart zacStater(@Qualifier("zacThings") GetThings<Set<String>> things) {
        return new DoStart(things);
    }

    @Bean
    public DoStart searchStater(@Qualifier("sThings") GetThings<Set<String>> things) {
        return new DoStart(things);

    }

    @Bean
    public GetThings<Set<String>> zacThings(Properties properties,
                                            ZipAndCutProperties zacProperties,
                                            @Qualifier("aLogger") HelloLogger searchLogger) {
        GetThings<Set<String>> things = DoStart.cGetThings(properties.getPath());
        things.setProperties(properties);
        things.setZacProperties(zacProperties);
        things.setHelloLogger(searchLogger);

        return things;
    }

    @Bean
    public GetThings<Set<String>> sThings(Properties properties, HelloLogger searchLogger) {
        GetThings<Set<String>> things = DoStart.cGetThings(properties.getPath());
        things.setProperties(properties);
        things.setHelloLogger(searchLogger);

        return things;
    }


}
