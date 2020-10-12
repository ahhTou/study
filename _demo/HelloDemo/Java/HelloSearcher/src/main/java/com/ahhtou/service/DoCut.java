package com.ahhtou.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
public class DoCut implements DoSomeForBB {

    private float goalH;
    private float goalW;

    GetThings<Map<String, BufferedImage>> getThings = new GetThings<>();

    @Override
    public GetThings<Map<String, BufferedImage>> getThings() {
        return this.getThings;
    }

    @Override
    public DoCut doSome(GetThings<Map<String, BufferedImage>> getThings) {

        this.goalH = getThings.getZacProperties().getHeight();
        this.goalW = getThings.getZacProperties().getWidth();

        Map<String, BufferedImage> map = new HashMap<>();

        getThings.getValue().forEach((k, v) -> map.put(k, toCut(v)));
        this.getThings = getThings.set(map);
        return this;
    }


    private BufferedImage toCut(BufferedImage buff) {
        float oW = buff.getWidth(), oH = buff.getHeight();
        int x = 0, y = 0;

        if (goalH / goalW <= oH / oW) {
            y = (int) ((oH - goalH) / 2);

        } else {
            x = (int) ((oW - goalW) / 2);
        }

        BufferedImage image = new BufferedImage(buff.getWidth(), buff.getHeight(), BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.drawImage(buff, -x, -y, buff.getWidth(), buff.getHeight(), null);
        return image.getSubimage(0, 0, Math.round(goalW), Math.round(goalH));

    }


}
