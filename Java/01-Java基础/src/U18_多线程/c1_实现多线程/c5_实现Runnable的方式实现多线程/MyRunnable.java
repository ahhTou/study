package U18_多线程.c1_实现多线程.c5_实现Runnable的方式实现多线程;

// 好处：不影响继承其他类
public class MyRunnable implements Runnable {
    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()
                    + ":" + i);
        }
    }
}
