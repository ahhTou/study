package U10_多态.c01;

public class AnimalDemo {
    public static void main(String[] args) {
        //  编译看左边 执行看右边
        Animal a = new Cat();
        a.eat();

        System.out.println(a.age);
//      System.out.println(a.weight);  //父类必须要有
    }
}
