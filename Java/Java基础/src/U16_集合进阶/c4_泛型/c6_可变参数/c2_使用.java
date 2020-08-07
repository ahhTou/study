package U16_集合进阶.c4_泛型.c6_可变参数;

import java.util.Arrays;
import java.util.List;

public class c2_使用 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "java");

//        不支持添加操作
//        list.add("a");

//        不支持移除操作
//        list.remove("hello")
        // 支持设置
        list.set(1, "the_World");

//        List<String> list2 =  List.of("hello", "world", "java");
//        增删改都不行

//        Set<String> set = Set.of("hello", "world", "java")
//        增删改都不行

    }
}
