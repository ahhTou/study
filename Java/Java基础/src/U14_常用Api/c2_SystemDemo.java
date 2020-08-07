package U14_常用Api;

public class c2_SystemDemo {
    public static void main(String[] args) {
        System.out.println("开始");


        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            System.out.println("123");
        }
        long end = System.currentTimeMillis();
        System.out.println("共耗时：" + (end - start) + "毫秒");
        System.out.println("当前时间：" + System.currentTimeMillis() * 1.0 / 1000 / 60 / 60 / 24 / 365 + "年");

        System.exit(0);
    }
}
