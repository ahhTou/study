package com.ahhtou.var;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ahhtou.utils.img.zip-and-cut")
@Data
@EqualsAndHashCode(callSuper = true)
public class ZipAndCutProperties extends Properties {

    private String suffixName;

    private int width;

    private int height;

}
