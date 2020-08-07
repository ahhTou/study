package U07_类的继承.c4;

public class Demo {
    public static void main(String[] args) {
        Zi z = new Zi();
        // 结果：
        // FU 无参构造方法
        // Zi 无参构造方法

        Zi z2 = new Zi(20);
        // 结果：
        // FU 无参构造方法
        // Zi 有参构造方法, 参数为：20

        // 子类所有的构造方法都默认访问父类中的无参方法
        //      1. 子类初始化前， 先完成父类的初始化
        //      2. 子类构造方法第一句默认： super()
    }
}
