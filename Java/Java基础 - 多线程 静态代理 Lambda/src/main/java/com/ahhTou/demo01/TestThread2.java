package com.ahhTou.demo01;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;

// 联系Thread， 实现多线程同步下载图片
public class TestThread2 implements Runnable {

    private final String url;
    private final String name;

    public TestThread2(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("下载了文件名为" + name + "的文件");
    }

    public static void main(String[] args) {
        String url = "https://img1.doubanio.com/view/photo/l/public/p2405993628.webp";
        TestThread2 t1 = new TestThread2(url, "1.jpg");
        TestThread2 t2 = new TestThread2(url, "2.jpg");
        TestThread2 t3 = new TestThread2(url, "3.jpg");
        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();
    }

}


// 下载器
class WebDownloader {

    // 下载方式
    public void downloader(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("IO异常，downloader方法出现问题");
        }
    }
}
