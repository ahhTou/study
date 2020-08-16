package com.ahhTou.lock8;

import java.util.concurrent.TimeUnit;

public class Test2 {
    public static void main(String[] args) {
        // 两个对象
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();
        new Thread(() -> {
            try {
                phone1.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(phone2::call, "B").start();
    }
}

class Phone2 {

    // synchronized 锁的对象是方法的调用者
    // 两个方法用的是一个锁,谁先拿到谁先执行

    public synchronized void sendSms() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("发短信");
    }

    public synchronized void call() {
        System.out.println("打电话");
    }

    // 没有锁
    public void hello() {
        System.out.println("hello");
    }
}

