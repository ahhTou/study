package U18_多线程.c2_线程同步.c1_案例_卖票;

public class SellTicket implements Runnable {
    private int tickets = 1;

    @Override
    public void run() {
        // 问题1：相同的票出现了多次
        while (true) {
            if (tickets > 0) {
                // 通过Sleep() 模拟出票时间

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(
                        Thread.currentThread().getName() +
                                "正在出售 第" + tickets + "张票");
                tickets--;
            }
        }

    }
}
