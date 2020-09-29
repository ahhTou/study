package com.ahhtou.var;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@ConfigurationProperties(prefix = "ahhtou.utils.img.zip-and-cut")
@Data
public class ZipAndCutProperties {

    private Set<String> path;

    private int width = 450;

    private int height = 450;

}
