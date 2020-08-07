package U23_函数式接口_Lambda扩展.c4_常见的函数式接口.c3_Predicate;

import java.util.function.Predicate;

public class c1_negate_test {
    public static void main(String[] args) {
        boolean b1 = checkString("hello",
                s -> s.length() > 8);

        boolean b2 = checkString("helloWorld",
                s -> s.length() > 8);

        System.out.println(b1);
        System.out.println(b2);
    }

    // 判断给定的字符串是否满足要求
    private static boolean checkString(String s, Predicate<String> pre) {
//        return !pre.test(s);
        return pre.negate().test(s); //逻辑非
    }
}
