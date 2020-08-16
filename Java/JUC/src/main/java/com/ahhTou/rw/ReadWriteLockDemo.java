package com.ahhTou.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        MyCacheLock myCache = new MyCacheLock();
//        MyCache myCache = new MyCache();

        CountDownLatch countDownLatch = new CountDownLatch(10);

        // 存取
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.put(temp + "", temp + "");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        // 读取
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.get(temp + "");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();
        myCache.show();
    }

}

class MyCacheLock {
    private final Map<String, Object> map = new HashMap<>();
    // 读写锁
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    // 存，写
    public void put(String key, Object value) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();

        }

    }

    // 取，读
    public void get(String key) {
        try {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "读取" + key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取Ok");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }


    }

    public void show() {
        System.out.println(map);
    }

}

class MyCache {
    private final Map<String, Object> map = new HashMap<>();

    // 存，写
    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + "写入" + key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "写入OK");
    }

    // 取，读
    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + "读取" + key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + "写入Ok");

    }

    public void show() {
        System.out.println(map.size());
    }

}
