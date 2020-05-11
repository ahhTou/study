package U24_Stream流.c3_Stream流常见中间操作;

import java.util.ArrayList;

public class c2_limit_skip {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("林青霞");
        list.add("张曼玉");
        list.add("王祖贤");
        list.add("柳岩");
        list.add("张敏");
        list.add("张无忌");

        // 取前三个
        list.stream().limit(3).forEach(System.out::println);

        System.out.println("=============");

        // 跳过前三个
        list.stream().skip(3).forEach(System.out::println);

        System.out.println("=============");

        // 跳2 剩下前两个输出
        list.stream().skip(2).limit(2).forEach(System.out::println);

    }
}
