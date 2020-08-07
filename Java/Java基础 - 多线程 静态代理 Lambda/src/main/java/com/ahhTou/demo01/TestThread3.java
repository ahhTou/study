package com.ahhTou.demo01;

// 创建线程方式2 实现runnable接口  执行线程需要丢入runnable接口实现类
public class TestThread3 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("我在看代码---" + i);

        }
    }

    public static void main(String[] args) {
        // 创建一个线程对象
        TestThread3 testThread3 = new TestThread3();
        // 创建线程对象，通过线程对象来开启我们的线程，代理
        new Thread(testThread3).start();

        // 调用start()方法开启线程
        for (int i = 0; i < 20; i++) {

            System.out.println("我在学习多线程--" + i);
        }
    }
}