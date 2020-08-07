package U16_集合进阶.c3_Set_以及比较器.c1_HashSet;

import java.util.LinkedHashSet;

public class c4_LInkedHashSet {
    public static void main(String[] args) {
        // 可迭代，顺序保证，没有重复的元素

        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<String>();

        linkedHashSet.add("hello");
        linkedHashSet.add("world");
        linkedHashSet.add("java");

        linkedHashSet.add("world");

        for (String s : linkedHashSet) {
            System.out.println(s);
        }
    }
}
