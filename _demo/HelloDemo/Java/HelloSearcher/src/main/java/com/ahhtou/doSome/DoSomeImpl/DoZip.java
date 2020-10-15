package com.ahhtou.doSome.DoSomeImpl;

import com.ahhtou.doSome.DoSomeForBB;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
public class DoZip implements DoSomeForBB {

    private float goalH;
    private float goalW;

    GetThings<Map<String, BufferedImage>> getThings = new GetThings<>();

    @Override
    public GetThings<Map<String, BufferedImage>> getThings() {
        return this.getThings;
    }

    @Override
    public DoZip doSome(GetThings<Map<String, BufferedImage>> getThings) {

        this.goalH = getThings.getZacProperties().getHeight();
        this.goalW = getThings.getZacProperties().getHeight();

        Map<String, BufferedImage> map = new HashMap<>();

        getThings.getValue().forEach((k, v) -> map.put(k, this.toZip(v)));

        this.getThings = getThings.set(map);

        return this;
    }

    private BufferedImage toZip(BufferedImage buff) {
        float oW = buff.getWidth(), oH = buff.getHeight();
        int zipWidth, zipHeight;
        float ratio;

        if (goalH / goalW <= oH / oW) {
            ratio = goalW / buff.getWidth();
            zipHeight = Math.round(ratio * buff.getHeight());
            zipWidth = Math.round(goalW);

        } else {
            ratio = goalH / buff.getHeight();
            zipWidth = Math.round(ratio * buff.getWidth());
            zipHeight = Math.round(goalH);

        }

        // 得到压缩的 buff
        BufferedImage image = new BufferedImage(zipWidth, zipHeight, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.drawImage(buff.getScaledInstance(zipWidth, zipHeight, Image.SCALE_SMOOTH), 0, 0, null);

        return image;
    }

}
