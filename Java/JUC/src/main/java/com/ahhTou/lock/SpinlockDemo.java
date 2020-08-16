package com.ahhTou.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/*

 */
public class SpinlockDemo {
    // int 0
    // Thread null
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    // 加锁
    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "==> myLock");

        // 自选锁
        while (!atomicReference.compareAndSet(null, thread)) {
        }
    }

    // 解锁
    public void unLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "==> myUnLock");
        atomicReference.compareAndSet(thread, null);
    }

    public void test(Boolean b) {
        while (b) {

        }
        System.out.println("dead");
    }

}

class TestDemo {
    public static void main(String[] args) throws InterruptedException {
        SpinlockDemo lock = new SpinlockDemo();


        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unLock();
        }, "T1").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            lock.lock();

            lock.unLock();
        }, "T2").start();
    }
}
