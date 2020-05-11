package U23_函数式接口_Lambda扩展.c4_常见的函数式接口.c2_Consumer;

import java.util.function.Consumer;

public class c1_ {
    public static void main(String[] args) {
        operatorString("ahhTou", s -> System.out.println(s));
        operatorString("ahhTou", System.out::println);

        operatorString("ahhTou", s -> System.out.println(
                new StringBuilder(s).reverse().toString()
        ));

        System.out.println("================");

        operatorString("ahhTou", System.out::println,
                s -> System.out.println(
                        new StringBuilder(s).reverse().toString()));


    }

    // 定义一个方法，用不同的方式消费同一个字符串两次
    private static void operatorString
    (String name, Consumer<String> con1, Consumer<String> con2) {
//        con1.accept(name);
//        con2.accept(name);
        con1.andThen(con2).accept(name);
    }


    // 定义一个方法，消费一个字符串数据
    private static void operatorString(String name, Consumer<String> con) {
        con.accept(name);
    }
}
