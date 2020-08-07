package U16_集合进阶.c4_泛型.c6_可变参数;

public class c1 {
    public static void main(String[] args) {
        System.out.println("和是 : " + sum(10, 20, 30));
        System.out.println("和是 : " + sum(10, 20));
        System.out.println("和是 : " + sum(10));

    }

    // 可变参数只能放到最后
    public static int sum(int... a) {
        // a 是一个数组
        int sum = 0;
        for (int i : a) {
            sum += i;
        }
        return sum;
    }
}
