package com.ahhTou.demo02;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.util.concurrent.*;

// 线程创建方式三 实现callable接口
/*
callable 的好处
1. 可以返回值
2. 可以抛出异常

 */
public class TestCallable implements Callable<Boolean> {
    private final String url;
    private final String name;

    public TestCallable(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("下载了文件名为" + name + "的文件");
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String url = "https://img1.doubanio.com/view/photo/l/public/p2405993628.webp";
        TestCallable t1 = new TestCallable(url, "1.jpg");
        TestCallable t2 = new TestCallable(url, "2.jpg");
        TestCallable t3 = new TestCallable(url, "3.jpg");

        // 创建执行服务
        ExecutorService ser = Executors.newFixedThreadPool(3);

        // 提交执行
        Future<Boolean> r1 = ser.submit(t1);
        Future<Boolean> r2 = ser.submit(t2);
        Future<Boolean> r3 = ser.submit(t3);

        // 获取结果
        boolean res1 = r1.get();
        boolean res2 = r2.get();
        boolean res3 = r3.get();

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);

        // 关闭服务
        ser.shutdown();

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