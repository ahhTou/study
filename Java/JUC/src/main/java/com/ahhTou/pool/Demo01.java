package com.ahhTou.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// Executors 工具类 3大方法
public class Demo01 {
    public static void main(String[] args) {
       /* ExecutorService threadPool1 = Executors.newSingleThreadExecutor();// 单个线程

        ExecutorService threadPool2 = Executors.newFixedThreadPool(5);// 创建一个固定的线程池的大小

        ExecutorService threadPool3 = Executors.newCachedThreadPool();// 可伸缩的，遇强则强，遇弱则弱*/

        System.out.println(Runtime.getRuntime().availableProcessors());
        final int MAX_CORE = Runtime.getRuntime().availableProcessors();


        ThreadPoolExecutor threadPool1 = new ThreadPoolExecutor(
                2,
                MAX_CORE,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3), // 银行候客厅
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy() // 队列满了，尝试和最早的任务竞争
        );

        // 最大承载 ： 队列 + max值
        for (int i = 0; i < 8; i++) {
            // 使用了线程池之后，使用线程池来创建线程
            threadPool1.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " Ok");
            });
        }

        // 线程池用完，程序结束，关闭线程池
        threadPool1.shutdown();

    }
}
