package U10_多态.c02多态的好处与坏处;

public class AnimalDemo {
    public static void main(String[] args) {
        //  创建动物操作类的对象，调用方法
        Animal a = new Cat();
        AnimalOperator animalOperator = new AnimalOperator();
        Cat c = new Cat();
        animalOperator.useAnimal(c);

        Dog d = new Dog();
        animalOperator.useAnimal(d);

        Pig p = new Pig();
        animalOperator.useAnimal(p);


    }
}
