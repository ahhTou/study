package com.ahhTou.future;


import com.ahhTou.pc.A;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/*
 异步调用 ： Ajax
    - 异步执行
    - 成功回调
    - 失败回调
 */
public class Demo01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

/*
        // 没有返回值的异步回调
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "runAsync=>Void");
        });

        System.out.println("111");
        completableFuture.get();
*/

        // 有返回结果的
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "supplyAsync=>Void");
            return 1024;
        });

        integerCompletableFuture.whenComplete((t, u) -> {
            System.out.println("t => " + t);
            System.out.println("u => " + u);
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return 233;
        });
    }

}
