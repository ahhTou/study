package U20_Lambda.c2_不带参;

public class EatableDemo {
    public static void main(String[] args) {
        Eatable e = new EatableImpl();
        useEatable(e);

        useEatable(new Eatable() {
            @Override
            public void eat() {
                System.out.println("匿名内部类，吃吃吃");
            }
        });

        useEatable(()->{
            System.out.println("lambda，吃吃吃");
        });
    }

    public static void useEatable(Eatable e) {
        e.eat();
    }
}
