package U23_函数式接口_Lambda扩展.c4_常见的函数式接口.c3_Predicate;

import java.util.function.Predicate;

public class c2_and_or {
    public static void main(String[] args) {
        boolean b1 = checkString("helloWorld",
                s -> s.length() > 8,
                s -> s.length() < 15);
        System.out.println(b1);
    }

    // 判断给定的字符串是否满足要求
    private static boolean checkString(
            String s,
            Predicate<String> pre1,
            Predicate<String> pre2) {
//        boolean b1 = pre1.test(s);
//        boolean b2 = pre2.test(s);
//        return b1 && b2;
//        return pre1.and(pre2).test(s);

        return pre1.or(pre2).test(s);
    }
}
