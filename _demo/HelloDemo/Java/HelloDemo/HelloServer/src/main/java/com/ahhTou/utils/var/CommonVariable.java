package com.ahhtou.utils.var;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@Data
@ConfigurationProperties(prefix = "ahhtou.utils.var")
public class CommonVariable {

    private String proxyPath;

    private String bgSearchPath;

    private Map<String, Integer> size;

    private String REDIS_BG_URLS_KEY;


}
