package U18_多线程.c1_实现多线程.c2_设置和获取线程名称;


public class MyThreadDemo {
    public static void main(String[] args) {
        MyThread my1 = new MyThread("高铁");
        MyThread my2 = new MyThread("飞机");

//        my1.setName("高铁");
//        my2.setName("飞机");

//        System.out.println(my1.getName());

        // 获取当前正在执行的线程对象引用


        my1.start();
        my2.start();

        System.out.println("当前运行的线程：" + Thread.currentThread().getName());
    }
}
