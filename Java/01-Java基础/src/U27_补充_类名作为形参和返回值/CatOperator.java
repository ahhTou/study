package U27_补充_类名作为形参和返回值;

public class CatOperator {
    public void useCat(Cat c) {
        c.eat();
    }

    public Cat getCat() {
        Cat c = new Cat();
        return c;
    }
}
