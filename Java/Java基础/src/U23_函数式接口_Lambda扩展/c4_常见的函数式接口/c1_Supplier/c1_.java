package U23_函数式接口_Lambda扩展.c4_常见的函数式接口.c1_Supplier;

import java.util.function.Supplier;

public class c1_ {
    public static void main(String[] args) {

        String s = getString(() -> "ahhTou");
        System.out.println(s);

        Integer i = getInteger(() -> 30);
        System.out.println(i);

    }

    private static Integer getInteger(Supplier<Integer> sup) {
        return sup.get();
    }


    private static String getString(Supplier<String> sup) {
        return sup.get();
    }
}
