package U18_多线程.c2_线程同步.c2_同步代码块或方法_解决数据安全问题;

public class SellTicket implements Runnable {
    private static int tickets = 100;
    private final Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            // 锁定代码块
            // 好处：解决了多线程数据安全问题
            // 弊端:当线程很多时，因为每个线程都会去判断同步上的锁，
            // 这是很耗费资源的,无形中会降低程序的运行效率
            synchronized (this) {
                // t1 进来后，就会把代码锁起来，其他代码没法进来
                if (tickets > 0) {
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
            // t1 出来后 代码会解锁
        }
    }

    // 加到函数上 , 锁是 this
    private synchronized void sellTicket() {
        if (tickets > 0) {
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

    // 静态方法的锁 不是 this ,是 SellTicket.class (类名.class)
    private static synchronized void sellTicket2() {
        if (tickets > 0) {
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
