package U23_函数式接口_Lambda扩展.c4_常见的函数式接口.c4_Function;

import java.util.function.Function;

public class c1_ {
    public static void main(String[] args) {
        convert("100", Integer::parseInt);

        convert(100, i -> String.valueOf(i + 566));

        convert("100", Integer::parseInt, i -> String.valueOf(i + 566));

    }

    // 字符串转整型
    private static void convert(String s, Function<String, Integer> fun) {
        int i = fun.apply(s);
        System.out.println(i);
    }

    // 整型转字符串
    private static void convert(int i, Function<Integer, String> fun) {
        String s = fun.apply(i);
        System.out.println(s);
    }

    //字符串转整形转字符串
    private static void convert(
            String s,
            Function<String, Integer> fun1,
            Function<Integer, String> fun2) {
        Integer i = fun1.apply(s);
        String ss = fun2.apply(i);
        System.out.println(ss);

        String apply = fun1.andThen(fun2).apply(s);

        System.out.println(apply);
    }
}
