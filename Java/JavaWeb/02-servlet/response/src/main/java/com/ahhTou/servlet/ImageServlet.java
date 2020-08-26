package com.ahhTou.servlet;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class ImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //
        resp.setHeader("refresh", "3");

        // 在内存中创建图片
        BufferedImage bufferedImage = new BufferedImage(80, 20, BufferedImage.TYPE_3BYTE_BGR);

        // 得到图片
        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, 80, 20);

        g.setColor(Color.black);
        // 给图片写数据
        g.setFont(new Font("宋体", Font.BOLD, 20));
        g.drawString(makeNum(), 0, 20);

        // 告诉浏览器，这个请求用图片的方式打开
        resp.setContentType("image/jpeg");

        // 不让浏览器缓存
        resp.setDateHeader("expires", -1);
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Pragma", "no-cache");

        //  把图片写给浏览器
        ImageIO.write(bufferedImage, "jpeg", resp.getOutputStream());


    }

    @Test
    public void test() {
        Random random = new Random();
        String v = random.nextDouble() + "";
        String substring = v.substring(2, 9);
        System.out.println(substring);
    }

    public String makeNum() {
        Random random = new Random();
        String v = random.nextDouble() + "";
        return v.substring(2, 9);
    }
}
