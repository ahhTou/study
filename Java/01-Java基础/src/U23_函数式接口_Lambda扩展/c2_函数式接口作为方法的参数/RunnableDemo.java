package U23_函数式接口_Lambda扩展.c2_函数式接口作为方法的参数;

public class RunnableDemo {
    public static void main(String[] args) {

        startThread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()
                        + " 启动了");
            }
        });

        startThread(() -> System.out.println(Thread.currentThread().getName()
                + " 启动了"));
    }

    private static void startThread(Runnable r) {
        new Thread(r).start();
    }
}
