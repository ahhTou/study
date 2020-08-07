package U20_Lambda.c3_带参;

public class FlyableDemo {
    public static void main(String[] args) {
        // 匿名内部类
        useFlyable(new Flyable() {
            @Override
            public void fly(String s) {
                System.out.println(s);
                System.out.println("匿名内部类");
            }
        });

        System.out.println("----");

        useFlyable((String s) -> {
            System.out.println(s);
            System.out.println("lambda 飞飞飞");
        });
    }

    private static void useFlyable(Flyable f) {
        f.fly("飞飞飞");
    }
}
