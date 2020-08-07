package U24_Stream流.c3_Stream流常见中间操作;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class c4_sorted {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("linqingxia");
        list.add("zhangmanyu");
        list.add("wangzuxian");
        list.add("liuyan");
        list.add("zhangmin");
        list.add("zhangwuji");

        // 按照字母顺序
//        list.stream().sorted().forEach(System.out::println);

        // 按照长度排序
        list.stream()
//                .sorted((s1, s2) -> s1.length() - s2.length())
                .sorted((s1, s2) -> {
                    int num = s1.length() - s2.length();
                    return num == 0 ? s1.compareTo(s2) : num;
                })
                .sorted(Comparator
                        .comparingInt(String::length)
                        .thenComparing(s -> s))
                .forEach(System.out::println);


    }
}
