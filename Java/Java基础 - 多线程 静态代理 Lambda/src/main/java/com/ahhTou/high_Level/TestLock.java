package com.ahhTou.high_Level;

import lombok.SneakyThrows;

import java.util.concurrent.locks.ReentrantLock;

public class TestLock {


    public static void main(String[] args) {
        TestLock2 testLock2 = new TestLock2();
        new Thread(testLock2).start();
        new Thread(testLock2).start();
        new Thread(testLock2).start();
    }

}

class TestLock2 implements Runnable {
    int ticketNums = 10;

    // 定义Lock锁
    private final ReentrantLock lock = new ReentrantLock();

    @SuppressWarnings("all")
    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                if (ticketNums > 0) {
                    Thread.sleep(1000);
                    System.out.println(ticketNums--);
                } else {
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
