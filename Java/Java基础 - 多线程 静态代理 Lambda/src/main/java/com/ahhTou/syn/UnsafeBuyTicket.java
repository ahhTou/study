package com.ahhTou.syn;

// 不安全买票
public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket station = new BuyTicket();

        new Thread(station, "我").start();
        new Thread(station, "你").start();
        new Thread(station, "黄牛党").start();
    }
}

class BuyTicket implements Runnable {

    private int ticketNums = 10;

    boolean flag = true; // 外部停止方式

    @Override
    public void run() {
        // 买票
        while (flag) {
            try {
                buy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void buy() {
        // 时候有票
        if (ticketNums <= 0) {
            flag = false;
            return;
        }
        // 买票
        System.out.println(Thread.currentThread().getName() + "拿到" + ticketNums--);
    }
}
