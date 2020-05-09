package U18_多线程.c1_实现多线程.c3_线程优先级;

public class ThreadPriority extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(getName() + ":" + i);
        }
    }
}
