package U20_Lambda.c1_Demo;

public class LambdaDemo {
    public static void main(String[] args) {
        // 方式1
        MyRunnable my = new MyRunnable();
        Thread t = new Thread(my);
        t.start();

        // 方式2
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("多线程实现了");
                    }
                }
        ).start();

        // 方式3 只有有上下文推断是lambda才能正确使用
        new Thread(() -> {
            System.out.println("多线程启动了");
        }).start();
    }

}
