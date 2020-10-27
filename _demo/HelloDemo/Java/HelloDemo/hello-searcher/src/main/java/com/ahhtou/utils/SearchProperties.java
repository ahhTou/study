package com.ahhtou.utils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "ahhtou.utils.img.search")
@Data
@EqualsAndHashCode(callSuper = true)
public class SearchProperties extends Properties{
}
