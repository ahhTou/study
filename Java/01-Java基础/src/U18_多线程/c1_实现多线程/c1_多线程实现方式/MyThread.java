package U18_多线程.c1_实现多线程.c1_多线程实现方式;

public class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("线程 ==" + i);
        }
    }
}
