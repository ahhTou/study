package com.ahhTou.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁 就是关于锁的8个问题
 */
public class Test1 {
    public static void main(String[] args) throws Exception {
        Phone phone = new Phone();
        new Thread(() -> {
            try {
                phone.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

//        TimeUnit.SECONDS.sleep(1);

        new Thread(phone::call, "B").start();
    }
}

class Phone {

    // synchronized 锁的对象是方法的调用者
    // 两个方法用的是一个锁,谁先拿到谁先执行

    public synchronized void sendSms() throws Exception {
        System.out.println("发短信");
    }

    public synchronized void call() {
        System.out.println("打电话");
    }
}
