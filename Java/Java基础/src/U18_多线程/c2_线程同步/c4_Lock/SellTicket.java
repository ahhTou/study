package U18_多线程.c2_线程同步.c4_Lock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SellTicket implements Runnable {
    private int tickets = 100;
    private final Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                if (tickets > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(
                            Thread.currentThread().getName()
                                    + "正在售出:" + tickets + "张票"
                    );
                    tickets--;
                    if (tickets == 0) {
                        break;
                    }
                }

            } finally {
                lock.unlock();
            }

        }
    }
}
