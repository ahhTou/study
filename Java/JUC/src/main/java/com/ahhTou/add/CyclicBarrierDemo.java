package com.ahhTou.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        /**
         * 集齐七颗龙珠召唤神龙
         *
         */
        // 召唤龙珠的线程

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙成功");
        });
        for (int i = 0; i < 8; i++) {
            final int temp = i; //1.8不用写
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "收集到了龙珠" + temp);

                try {
                    cyclicBarrier.await(); // 当计数为7时将被唤醒
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
