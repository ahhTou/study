package com.ahhtou.doSome.DoSomeImpl;

import com.ahhtou.doSome.DoSomeForBS;
import com.ahhtou.logger.HelloLogger;
import com.ahhtou.webp.HelloWebp;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DoWrite implements DoSomeForBS {

    GetThings<Set<String>> getThings = new GetThings<>();
    HelloLogger log = null;


    @Override
    public GetThings<Set<String>> getThings() {
        return this.getThings;
    }

    @Override
    public DoWrite doSome(GetThings<Map<String, BufferedImage>> getThings) {
        log = getThings.getHelloLogger();
        Set<String> set = new HashSet<>();
        try {
            getThings.getValue().forEach((k, v) -> {
                this.writeWebpImage(v, k);
                log.logBlue("写入了文件 -> " + k);
                set.add(k);
            });
        } catch (Exception e) {
            e.printStackTrace();
            set.add(null);
        }
        this.getThings = getThings.set(set);
        return this;
    }

    private void writeWebpImage(BufferedImage buff, String outUrl) {
        HelloWebp.getWriter().setBuff(buff).setOutPath(outUrl).write();
    }
}
