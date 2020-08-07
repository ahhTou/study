package com.ahhTou.state;

import java.text.SimpleDateFormat;
import java.util.Date;

// 模拟倒计时
public class TestSleep {
    public static void main(String[] args) {

        // 打印系统当前时间
        Date statTime = new Date(System.currentTimeMillis());
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println(new SimpleDateFormat("HH:mm:ss").format(statTime));
                statTime = new Date(System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void tenDown() throws InterruptedException {
        int num = 10;
        do {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "-->" + num--);
        } while (num > 0);
    }

}
