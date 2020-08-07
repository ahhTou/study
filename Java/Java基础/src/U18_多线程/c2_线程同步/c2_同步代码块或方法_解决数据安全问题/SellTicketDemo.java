package U18_多线程.c2_线程同步.c2_同步代码块或方法_解决数据安全问题;
/*
    1.是否是多线程环境          | 不能破坏
    2.是否有共享数据           | 不能破坏
    3.是否多条语句操作共享数据      | 锁定
 */


public class SellTicketDemo {
    public static void main(String[] args) {

        SellTicket st = new SellTicket();

        Thread t1 = new Thread(st, "窗口1");
        Thread t2 = new Thread(st, "窗口2");
        Thread t3 = new Thread(st, "窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
