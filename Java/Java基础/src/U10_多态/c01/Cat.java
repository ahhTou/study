package U10_多态.c01;

public class Cat extends Animal {
    public int weight = 30;

    @Override
    public void eat() {
        System.out.println("猫吃鱼");
    }

}
