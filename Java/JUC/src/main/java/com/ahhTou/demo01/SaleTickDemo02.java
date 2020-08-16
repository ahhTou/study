package com.ahhTou.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTickDemo02 {
    public static void main(String[] args) {
        Ticket2 ticket = new Ticket2();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket.sale();
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }).start();
    }
}

// lock 三部曲
// 1. new ReentrantLock
// 2. lock.lock() 加锁
// finally ==> lock.unlock();
class Ticket2 {

    private int number = 50;

    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        lock.tryLock();

        try {
            //业务代码
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "第几张票");
            }
        } finally {
            lock.unlock();
        }
    }

}