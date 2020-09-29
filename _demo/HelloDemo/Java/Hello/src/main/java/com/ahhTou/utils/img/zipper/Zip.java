package com.ahhTou.utils.img.zipper;

import com.ahhTou.utils.img.webp.MyWebp;
import lombok.Data;

import java.awt.*;
import java.awt.image.BufferedImage;

@Data
public class Zip {

    private ImgZipperProperties conf;

    private BufferedImage buff;

    private String OLD_IMG_URL;

    private String NEW_IMG_URL;

    private float GOAL_HEIGHT;

    private float GOAL_WIDTH;


    public Zip(ImgZipperProperties conf) {
        this.conf = conf;
        this.GOAL_HEIGHT = conf.getHeight();
        this.GOAL_WIDTH = conf.getWidth();
    }

    public void start(String url) {
        this.get(url).zip().cut().write();
    }

    public Zip get(String url) {
        OLD_IMG_URL = url;
        try {
            // 得到图片目标地址
            NEW_IMG_URL = new Utils(conf).newImageUrl(OLD_IMG_URL);

            // 得到图片buff
            buff = new MyWebp().getReader().getBuff(OLD_IMG_URL);

            return this;
        } catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException("获取图片异常");

        }

    }


    public Zip zip() {
        float oW = buff.getWidth(), oH = buff.getHeight();
        int zipWidth, zipHeight;
        float ratio;
        try {
            if (GOAL_HEIGHT / GOAL_WIDTH <= oH / oW) {
                ratio = GOAL_WIDTH / buff.getWidth();
                zipHeight = Math.round(ratio * buff.getHeight());
                zipWidth = Math.round(GOAL_WIDTH);

            } else {
                ratio = (float) GOAL_HEIGHT / buff.getHeight();
                zipWidth = Math.round(ratio * buff.getWidth());
                zipHeight = Math.round((float) GOAL_HEIGHT);

            }

            // 得到压缩的 buff
            BufferedImage image = new BufferedImage(zipWidth, zipHeight, BufferedImage.TYPE_INT_BGR);
            Graphics g = image.getGraphics();
            g.drawImage(buff.getScaledInstance(zipWidth, zipHeight, Image.SCALE_SMOOTH), 0, 0, null);

            buff = image;


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("压缩图片出现异常");
        }

        return this;


    }


    public Zip cut() {
        float oW = buff.getWidth(), oH = buff.getHeight();
        int x = 0, y = 0;

        if (GOAL_HEIGHT / GOAL_WIDTH <= oH / oW) {
            y = (int) ((oH - GOAL_HEIGHT) / 2);

        } else {
            x = (int) ((oW - GOAL_WIDTH) / 2);

        }

        BufferedImage image = new BufferedImage(buff.getWidth(), buff.getHeight(), BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.drawImage(buff, -x, -y, buff.getWidth(), buff.getHeight(), null);
        buff = image.getSubimage(0, 0, Math.round(GOAL_WIDTH), Math.round(GOAL_HEIGHT));
        return this;
    }


    public void write() {


        new MyWebp().getWriter()
                .setBuff(buff)
                .setOutPutUrl(NEW_IMG_URL)
                .write();

        System.out.println("Zipper -> 压缩并得到了 ： " + NEW_IMG_URL);
    }


}
