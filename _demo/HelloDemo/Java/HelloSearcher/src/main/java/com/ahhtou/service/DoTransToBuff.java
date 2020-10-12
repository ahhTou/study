package com.ahhtou.service;


import com.ahhtou.webp.HelloWebp;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DoTransToBuff implements DoSomeForSB {

    Map<String, BufferedImage> map = new HashMap<>();
    GetThings<Map<String, BufferedImage>> getThings = new GetThings<>();


    @Override
    public DoTransToBuff doSome(GetThings<Set<String>> getThings) {
        getThings.getValue().forEach(l -> map.put(l, this.toWebpBuff(l)));
        this.getThings = getThings.set(map);
        return this;
    }

    @Override
    public GetThings<Map<String, BufferedImage>> getThings() {
        return this.getThings;
    }

    private BufferedImage toWebpBuff(String url) {
        return HelloWebp.getReader().getBuff(url);
    }


}
