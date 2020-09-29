package com.ahhTou.utils.img.webp;


import com.luciad.imageio.webp.WebPReadParam;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;

public class MyWebpReader {

    private final ImageReader reader;

    public MyWebpReader() {
        this.reader = ImageIO.getImageReadersByMIMEType("image/webp").next();
    }

    public BufferedImage getBuff(String url) {
        try {
            // 得到图片

            WebPReadParam readParam = new WebPReadParam();
            readParam.setBypassFiltering(true);

            // 得到buff
            reader.setInput(new FileImageInputStream(new File(url)));
            return reader.read(0, readParam);

        } catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException("得到BufferedImage出错");

        }


    }
}
