package U24_Stream流.c1_Stream流体验;

import java.util.ArrayList;

public class StreamDemo {
    public static void main(String[] args) {
        // 需求，把姓张的存到一个集合里面，把姓张而且名字数为3的存在里面
        ArrayList<String> list = new ArrayList<>();
        list.add("林青霞");
        list.add("张曼玉");
        list.add("王祖贤");
        list.add("柳岩");
        list.add("张敏");
        list.add("张无忌");

        ArrayList<String> zList = new ArrayList<>();

        list.forEach(s -> {
            if (s.startsWith("张")) {
                zList.add(s);
            }
        });

        System.out.println(zList);
        ArrayList<String> threeList = new ArrayList<>();

        zList.forEach(s -> {
            if (s.length() == 3) {
                threeList.add(s);
            }
        });

        System.out.println(threeList);

        threeList.forEach(System.out::println);


        System.out.println("===============");
        System.out.println("lambda表达式:");

        // Stream流
        list.stream()
                .filter(s -> s.startsWith("张"))
                .filter(s -> s.length() == 3)
                .forEach(System.out::println);


    }
}
