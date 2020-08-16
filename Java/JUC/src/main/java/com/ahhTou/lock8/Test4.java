package com.ahhTou.lock8;

import java.util.concurrent.TimeUnit;

public class Test4 {
    public static void main(String[] args) {
        Phone4 phone = new Phone4();
        Phone4 phone2 = new Phone4();
        new Thread(() -> {
            try {
                phone.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                phone2.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B1").start();

    }
}


class Phone4 {
    // synchronized 锁的对象是方法的调用者
    // static 静态方法
    // 类一加载就有了 Class模板，synchronized锁的是Class(全局唯一)
    public static synchronized void sendSms() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("发短信");
    }

    public synchronized void call() {
        System.out.println("打电话");
    }

}