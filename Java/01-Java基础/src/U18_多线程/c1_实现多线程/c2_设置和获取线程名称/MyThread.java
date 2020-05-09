package U18_多线程.c1_实现多线程.c2_设置和获取线程名称;

public class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(getName() + ":" + i);
        }
    }

    public MyThread() {
    }

    public MyThread(String name) {
        super(name);
    }
}
