package U23_函数式接口_Lambda扩展.c4_常见的函数式接口.c4_Function;

import U12_接口.c2特点.Inter;

import java.util.function.Function;

public class c2_练习 {
    public static void main(String[] args) {
        String s = "林青霞,30";
        convert(s,
                ss -> ss.split(",")[1],
                ss -> Integer.parseInt(ss),
                i -> i + 70);


    }

    private static void convert(
            String s,
            Function<String, String> fun1,
            Function<String, Integer> fun2,
            Function<Integer, Integer> fun3) {
        int i = fun1.andThen(fun2).andThen(fun3).apply(s);
        System.out.println(i);

    }
}
