package com.ahhtou.zac;

import com.ahhtou.utils.UrlUtils;
import com.ahhtou.var.ZipAndCutProperties;

import java.awt.image.BufferedImage;
import java.util.Map;

public class DoZacTrans{

/*    ZipAndCutProperties properties;

    public DoZacTrans(ZipAndCutProperties properties) {
        this.properties = properties;
    }

    @Override
    public DoSome<Map<String, BufferedImage>, Map<String, BufferedImage>> doSome(Map<String, BufferedImage> buffs) {
        buffs.forEach((k, v) -> {
            buffs.remove(k);
            buffs.put(this.newImageUrl(k), v);
        });
        things = buffs;
        return this;
    }

    public String newImageUrl(String oldUrl) {
        return UrlUtils.getFileNameNoSuffix(oldUrl) + properties.getSuffixName() + UrlUtils.getSuffixName(oldUrl);
    }*/
}
