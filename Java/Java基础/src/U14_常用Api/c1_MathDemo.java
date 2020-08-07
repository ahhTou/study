package U14_常用Api;

public class c1_MathDemo {
    public static void main(String[] args) {
        System.out.println("绝对值" + Math.abs(-100));

        System.out.println("返回大于或等于的最小值double值：" + Math.ceil(12.34));
        System.out.println("返回大于或等于的最小值double值：" + Math.ceil(12.99));

        System.out.println("返回小于或等于的最大值double值：" + Math.floor(12.34));
        System.out.println("返回小于或等于的最大值double值：" + Math.floor(12.99));

        System.out.println("四舍五入int：" + Math.round(12.34));
        System.out.println("四舍五入int：" + Math.round(12.99));

        System.out.println("反回两个值中最大值：" + Math.max(12.99, 999));

        System.out.println("返回 a的b次幂：" + Math.pow(10.0, 2));

        System.out.println("返回0-100的随机数：" + Math.random() * 100 + 1);

    }
}
