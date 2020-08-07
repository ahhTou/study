package U07_类的继承.c4;

public class Zi extends Fu {
    public Zi() {
        super(20); //父类没有无参构造函数时，手动调用父类
        System.out.println("Zi 无参构造方法");
    }

    public Zi(int age) {
        super(20);
        System.out.println("Zi 有参构造方法, 参数为：" + age);
    }
}
