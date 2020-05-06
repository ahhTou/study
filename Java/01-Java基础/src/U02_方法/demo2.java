package U02_方法;

public class demo2 {
    public static void main(String[] args) {
        int a = sum(1, 3, 3);
        double b = sum(10.0, 20.0);
        int c = sum(1, 10);
    }
    // 方法重载
    // 1. 多个方法在同一个类中
    // 2. 多个方法具有相同的方法名
    // 3. 多个方法参数不相同，类型不同活数量不同

    public static double sum(double a, double b) {
        return a + b;
    }

    public static int sum(int x, int y) {
        return x + y;
    }


    public static int sum(int a, int b, int c) {
        return a + b + c;
    }

}
