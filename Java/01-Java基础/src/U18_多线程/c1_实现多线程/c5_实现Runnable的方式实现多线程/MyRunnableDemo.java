package U18_多线程.c1_实现多线程.c5_实现Runnable的方式实现多线程;

public class MyRunnableDemo {
    public static void main(String[] args) {
        MyRunnable my = new MyRunnable();

        Thread t1 = new Thread(my, "ahh");
        Thread t2 = new Thread(my, "Tou");

        t1.start();
        t2.start();
    }
}
