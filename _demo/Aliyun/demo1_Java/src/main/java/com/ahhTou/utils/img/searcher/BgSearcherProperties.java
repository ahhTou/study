package com.ahhTou.utils.img.searcher;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "ahhtou.utils.img.searcher")
public class BgSearcherProperties {

    private List<String> imgRegs = Arrays.asList(".*.jpg", ".*.png");
    private Integer cycleTime = 5;


}
