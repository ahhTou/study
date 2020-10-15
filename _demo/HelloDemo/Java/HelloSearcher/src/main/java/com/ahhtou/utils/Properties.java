package com.ahhtou.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@ConfigurationProperties(prefix = "ahhtou.utils.img")
@Data
public class Properties {

    private String redisBgImagesUrlSaveKey;

    private Set<String> imgSuffixReg;

    private Set<String> path;

    private int width;

    private int height;

    private String proxyPath;

    private int cycleTime;


}
