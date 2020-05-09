package U18_多线程.c1_实现多线程.c4_线程控制;

public class ThreadDaemonDemo extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(getName() + ":" + i);
        }
    }
}
