package U16_集合进阶.c3_Set_以及比较器.c1_HashSet;

import java.util.HashSet;
import java.util.Set;

public class c1_HashSet {
    public static void main(String[] args) {
        // Set 不包含重复，没有索引
        // HashSet 对集合的迭代顺序不做保证

        Set<String> set = new HashSet<String >();

        set.add("hello");
        set.add("world");
        set.add("java");
        set.add("java"); //无法添加，不能添加重复


        for (String s : set){
            System.out.println(s);
        }



    }
}
