package com.ahhtou.var;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@ConfigurationProperties(prefix = "ahhtou.utils.img")
@Data
public class Properties {

    private String RedisBgImagesUrlSaveKey;

    private Set<String> ImgSuffixReg;

    private SearchProperties search;

    private ZipAndCutProperties zipAndCut;

    private Set<String> path;


}
