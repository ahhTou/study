package U11_抽象类.c4抽象类名作为形参和返回值;

public class AnimalOperator {
    public void useAnimal(Animal a) {
        a.eat();
    }

    public Animal getAnimal() {
        Animal a = new Cat();
        return a;
    }

}
