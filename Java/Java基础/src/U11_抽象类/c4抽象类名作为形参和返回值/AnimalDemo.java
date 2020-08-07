package U11_抽象类.c4抽象类名作为形参和返回值;

public class AnimalDemo {
    public static void main(String[] args) {
        AnimalOperator ao = new AnimalOperator();
        Animal a = new Cat();

        ao.useAnimal(a);

        Animal a2 = ao.getAnimal();
        a2.eat();
    }
}
