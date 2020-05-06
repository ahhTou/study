package U09_修饰符;

public class Zi extends Fu {

    public final int age = 20;
    public String name = "ahhTou";

    public void show() {
        System.out.println("age:" + age);
        System.out.println("name:" + name);
    }

//     1. 无法重写final的方法
//    @Override
//    public void method() {
//        System.out.println("Zi");
//    }
    // 2. 无法修饰final的 常量
    // 3. 无法继承final 的类
}
