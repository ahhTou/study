package U18_多线程.c1_实现多线程.c3_线程优先级;

public class ThreadPriorityDemo {

    public static void main(String[] args) {

        ThreadPriority tp1 = new ThreadPriority();
        ThreadPriority tp2 = new ThreadPriority();
        ThreadPriority tp3 = new ThreadPriority();

        tp1.setName("高铁");
        tp2.setName("飞机");
        tp3.setName("汽车");


        // 1.获取优先级 默认优先级：5
//        System.out.println(tp1.getPriority());
//        System.out.println(tp2.getPriority());
//        System.out.println(tp3.getPriority());

        // 2.设置优先级 最大10 最小1 ,几率高
        System.out.println("最大优先级" + Thread.MAX_PRIORITY);
        System.out.println("最小优先级" + Thread.MIN_PRIORITY);

        tp1.setPriority(5);
        tp2.setPriority(10);
        tp3.setPriority(1);

        tp1.start();
        tp2.start();
        tp3.start();

    }
}
