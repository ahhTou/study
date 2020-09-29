package com.ahhtou.var;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@ConfigurationProperties(prefix = "ahhtou.utils.img.search")
@Data
public class SearchProperties {

    private Set<String> path;

}
