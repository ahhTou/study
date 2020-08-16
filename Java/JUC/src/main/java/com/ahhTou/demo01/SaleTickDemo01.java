package com.ahhTou.demo01;

/**
 * 真正多线程开发，公司中的开发，降低耦合性
 * 线程就剩应该单独的资源类，没有任何附属的操作
 * 1、属性，方法
 */
public class SaleTickDemo01 {
    public static void main(String[] args) {
        // 并发：多线程操作
        Ticket ticket = new Ticket();
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
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }).start();
    }
}

// 资源类
class Ticket {

    private int number = 50;

    // 卖票的方式
    // synchronized 本质 队列 和 锁
    public synchronized void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "第几张票");
        }
    }

}
