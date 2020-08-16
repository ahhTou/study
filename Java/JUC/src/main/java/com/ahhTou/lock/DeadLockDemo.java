package com.ahhTou.lock;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class DeadLockDemo {

    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();

        new Thread(new MyThread(a, b), "T1").start();
        new Thread(new MyThread(b, a), "T2").start();
    }

}


class MyThread implements Runnable {
    private final Object lockA;
    private final Object lockB;

    public MyThread(Object lockA, Object lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @SneakyThrows
    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + " lock:" + lockA + "=> getLockB" + lockB);

            TimeUnit.SECONDS.sleep(2);

            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + " lock:" + lockB + "=> getLockA" + lockA);
            }
        }
    }
}
