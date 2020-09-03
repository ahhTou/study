package com.ahhTou.utils.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "ahhtou.utils.bg-searcher")
public class BgSearcherProperties {

    private List<String> imgRegs = Arrays.asList(".*.jpg", ".*.png");
    private String localUrl = "C:\\Users\\11979\\OneDrive\\图片";
    private String proxyUrl = "http://39.99.154.145/img/hello/";
    private int cycleTime = 1;



}
