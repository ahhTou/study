package com.ahhTou.state;

// 观察测试线程状态
public class TestState {


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("////////////");
        });
        Thread.State state = thread.getState();
        System.out.println(state); // new

        // 观察启动后
        thread.start(); // 启动线程
        state = thread.getState();
        System.out.println(state); // Run

        while (state != Thread.State.TERMINATED) {
            Thread.sleep(100);
            state = thread.getState();
            System.out.println(state);
        }

    }

}
