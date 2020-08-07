package U18_多线程.c1_实现多线程.c4_线程控制;

public class ThreadDaemon {
    public static void main(String[] args) {
        ThreadJoin ts1 = new ThreadJoin();
        ThreadJoin ts2 = new ThreadJoin();

        ts1.setName("Tou");
        ts2.setName("ahh");

        // 设置主线程
        Thread.currentThread().setName("ahhTou");

        // 设置守护线程,主线程执行完毕后，其他也要跟着结束
        ts1.setDaemon(true);
        ts2.setDaemon(true);

        ts1.start();
        ts2.start();

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()
                    + ":" + i);
        }
    }
}
