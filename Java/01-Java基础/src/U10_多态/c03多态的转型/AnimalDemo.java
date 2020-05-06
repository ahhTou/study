package U10_多态.c03多态的转型;

public class AnimalDemo {
    public static void main(String[] args) {
        Animal a = new Cat();
        a.eat();
//        a.playGame();

//        Cat c = new Cat();
//        c.eat();
//        c.playGame();

        // 向下转型
        Cat c = (Cat) a;
        c.playGame();
    }
}
