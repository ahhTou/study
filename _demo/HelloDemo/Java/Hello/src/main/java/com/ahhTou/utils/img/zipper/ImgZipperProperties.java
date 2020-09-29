package com.ahhTou.utils.img.zipper;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "ahhtou.utils.img.zipper")
public class ImgZipperProperties {

    private String searchPath = "C:\\Users\\11979\\OneDrive\\图片\\Hello\\mobile";


    private String suffixName = "_bg_changer";

    private List<String> imgRegs = Collections.singletonList(".*.webp");

    private int width = 150;

    private int height = 250;

}
