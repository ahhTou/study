package U24_Stream流.c3_Stream流常见中间操作;

import java.util.ArrayList;
import java.util.stream.Stream;

public class c3_concat_distinct {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("林青霞");
        list.add("张曼玉");
        list.add("王祖贤");
        list.add("柳岩");
        list.add("张敏");
        list.add("张无忌");


        // 1.取前4个数据组成一个流
        Stream<String> s1 = list.stream().limit(4);

        // 2.跳过2个数据组成一个流
        Stream<String> s2 = list.stream().skip(2);

        // 3. 合并 1、2 并遍历

//        Stream.concat(s1, s2).forEach(System.out::println);

        System.out.println("============");

        // 4. 合并 去重
        Stream.concat(s1, s2).distinct().forEach(System.out::println);

    }
}
