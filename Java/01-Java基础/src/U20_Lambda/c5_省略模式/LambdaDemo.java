package U20_Lambda.c5_省略模式;

public class LambdaDemo {
    public static void main(String[] args) {
//        useAddable((int x, int y)->{
//            return x + y;
//        });

        // 1. 参数可以省略，要么多省略，要么不省略
        useAddable((x, y) -> {
            return x + y;
        });

        // 2. 参数有且只有一个 ，小括号可以省略
        useFlyable(s -> {
            System.out.println(s);
        });

        // 3. 如果语句只有一条，可以省略大括号
        useFlyable(s -> System.out.println(s));

        // 4. 如果是return 可以省略是return
        useAddable((x, y) -> x + y);

    }

    private static void useFlyable(Flyable f) {
        f.fly("飞飞飞");
    }

    private static void useAddable(Addable a) {
        int sum = a.add(10, 20);
        System.out.println(sum);
    }
}
