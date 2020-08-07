package com.ahhTou.demo01;

// 创建线程方式： 继承Thread类，重写run()方法，调用start()

// 总结： 注意，进程开启不一定立即执行，由cpu调度执行
public class TestThread1 extends Thread {

    // 重写run方法，作为程序主体
    @Override
    public void run() {

        for (int i = 0; i < 20; i++) {

            System.out.println("我在看代码---" + i);

        }
    }

    public static void main(String[] args) {
        // main 主线程

        // 创建一个线程对象
        TestThread1 testThread1 = new TestThread1();

        // 调用start()方法开启线程
        testThread1.start();

        for (int i = 0; i < 20; i++) {

            System.out.println("我在学习多线程--" + i);
        }
    }
}
