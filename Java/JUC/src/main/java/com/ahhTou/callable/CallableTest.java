package com.ahhTou.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // new Thread(new Runnable()).start();
        // new Thread(new FutureTask<V>()).start(); // Runnable实现类
        // new Thread(new FutureTask<V>( Callable )).start(); // Runnable实现类

        MyThread thread = new MyThread();

        FutureTask<String> futureTask = new FutureTask<>(thread);

        new Thread(futureTask, "A").start();
        new Thread(futureTask, "A").start(); // 结果会被缓存，效率高

        String s = futureTask.get(); // 可能会产生阻塞

        System.out.println(s);

    }
}

class MyThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("小夫，我进来了");
        return "ahhTou";
    }
}
