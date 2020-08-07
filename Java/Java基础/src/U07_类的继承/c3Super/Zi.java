package U07_类的继承.c3Super;

public class Zi extends Fu {
    public int height = 175;
    public int age = 20;

    public void show() {
        // 自上往下，类似于就近原则
        int age = 30;
        // 使用 this.age 直接访问成员变量
        System.out.println(this.age);
        System.out.println(height);
    }
}
