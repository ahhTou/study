package U18_多线程.c1_实现多线程.c4_线程控制;

public class ThreadJoinDemo {
    public static void main(String[] args) {
        ThreadJoin ts1 = new ThreadJoin();
        ThreadJoin ts2 = new ThreadJoin();
        ThreadJoin ts3 = new ThreadJoin();

        ts1.setName("曹操");
        ts2.setName("刘备");
        ts3.setName("孙权");

        ts1.start();
        try {
            // 这个线程死亡后其他线程才开始
            ts1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ts2.start();
        ts3.start();
    }
}
