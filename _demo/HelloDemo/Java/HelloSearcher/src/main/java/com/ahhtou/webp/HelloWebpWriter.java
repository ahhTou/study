package com.ahhtou.webp;


import com.luciad.imageio.webp.WebPWriteParam;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;

public class HelloWebpWriter {

    String outPath;
    BufferedImage buff;

    ImageWriter writer;

    public HelloWebpWriter() {
        this.writer = ImageIO.getImageWritersByMIMEType("image/webp").next();
    }

    public HelloWebpWriter setOutPath(String url) {
        outPath = url;
        return this;
    }

    public HelloWebpWriter setBuff(BufferedImage buff) {
        this.buff = buff;
        return this;
    }


    public void write() {


        if (outPath == null) throw new RuntimeException("未设置输出地址");
        if (buff == null) throw new RuntimeException("未设置输出图片");
        try {

            WebPWriteParam writeParam = new WebPWriteParam(writer.getLocale());
            writeParam.setCompressionMode(1);
            writer.setOutput(new FileImageOutputStream(new File(outPath)));
            writer.write(null, new IIOImage(buff, null, null), writeParam);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("出现异常");
        }
        outPath = null;
        buff = null;

    }


}
