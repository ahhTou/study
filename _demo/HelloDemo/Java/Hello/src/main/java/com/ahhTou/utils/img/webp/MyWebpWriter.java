package com.ahhTou.utils.img.webp;


import com.luciad.imageio.webp.WebPWriteParam;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;

public class MyWebpWriter {

    String outPutUrl;
    BufferedImage buff;

    // webp writer
    ImageWriter writer;

    public MyWebpWriter() {
        this.writer = ImageIO.getImageWritersByMIMEType("image/webp").next();
        ;
    }

    public MyWebpWriter setOutPutUrl(String url) {
        outPutUrl = url;
        return this;
    }

    public MyWebpWriter setBuff(BufferedImage buff) {
        this.buff = buff;
        return this;
    }


    public void write() {


        if (outPutUrl == null) throw new RuntimeException("未设置输出地址");
        if (buff == null) throw new RuntimeException("未设置输出图片");
        try {

            WebPWriteParam writeParam = new WebPWriteParam(writer.getLocale());
            writeParam.setCompressionMode(1);
            writer.setOutput(new FileImageOutputStream(new File(outPutUrl)));
            writer.write(null, new IIOImage(buff, null, null), writeParam);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("出现异常");
        }

        outPutUrl = null;
        buff = null;

    }


}
