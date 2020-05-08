package U16_集合进阶.c3_Set.c1_HashSet;

import java.util.HashSet;
import java.util.Set;

public class c2_HashSet集合的特点 {
    public static void main(String[] args) {
        // 底层数据结构是哈希表
        // 对集合的迭代顺序不做保证，也就是不保证存取取出顺序一致
        // 没有索引的方法 所以不能使用普通的for循环
        // Set集合，不包含重复的集合

        Set<String> hs = new HashSet<String>();

        hs.add("hello");
        hs.add("world");
        hs.add("java");

        hs.add("java");

        for (String s : hs) {
            System.out.println(s);
        }
    }
}
