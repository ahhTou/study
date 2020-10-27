package com.ahhtou.doSome.DoSomeImpl;

import com.ahhtou.doSome.DoSomeForBB;
import com.ahhtou.doSome.GetThings;
import com.ahhtou.utils.UrlUtils;
import com.ahhtou.utils.ZipAndCutProperties;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class DoAddSuffix implements DoSomeForBB {

    ZipAndCutProperties properties;
    GetThings<Map<String, BufferedImage>> getThings = new GetThings<>();


    @Override
    public GetThings<Map<String, BufferedImage>> getThings() {
        return getThings;
    }

    @Override
    public DoAddSuffix doSome(GetThings<Map<String, BufferedImage>> getThings) {
        properties = getThings.getZacProperties();
        Map<String, BufferedImage> map = new HashMap<>();

        getThings.getValue().forEach((k, v) -> {
            map.put(this.newImageUrl(k), v);
        });

        this.getThings = getThings.set(map);

        return this;
    }

    public String newImageUrl(String oldUrl) {
        return UrlUtils.getFileNameNoSuffix(oldUrl) + properties.getSuffixName() + UrlUtils.getSuffixName(oldUrl);
    }
}
