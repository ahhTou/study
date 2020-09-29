package com.ahhTou.utils.img.searcher;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "ahhtou.utils.img.searcher")
public class ImgSearcherProperties {

    private List<String> imgRegs;
    private Integer cycleTime = 5;
    private String proxyUrl = null;


}
