package com.ahhTou.tvolatile;

import java.util.concurrent.TimeUnit;

public class JMMDemo {
    // 不加 volatile 程序就会进行死循环
    // 加 volatile 会保证 可见性
    private static volatile int num = 0; //volatile关键字可以保证变量改变时被其他线程感知

    public static void main(String[] args) throws InterruptedException {
        // main 线程

        new Thread(() -> { // 线程1 对主内存的变坏不知道
            while (num == 0) {

            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        num = 1; // 线程应当停止
        System.out.println(num);
    }
}
