package com.ahhTou.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 增加两个静态的同步方法
 */
public class Test3 {

    public static void main(String[] args) {
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();
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

        new Thread(() -> {
            try {
                phone2.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();

    }


}

class Phone3 {

    // synchronized 锁的对象是方法的调用者
    // static 静态方法
    // 类一加载就有了 Class模板，synchronized锁的是Class(全局唯一)
    public static synchronized void sendSms() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("发短信");
    }

    public static synchronized void call() {
        System.out.println("打电话");
    }

}