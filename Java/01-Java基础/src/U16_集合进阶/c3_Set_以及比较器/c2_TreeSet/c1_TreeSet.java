package U16_集合进阶.c3_Set_以及比较器.c2_TreeSet;

import java.util.TreeSet;

public class c1_TreeSet {
    public static void main(String[] args) {
        // 1.有序 但是不是指储存取出是顺序，而是排序方式 取决于构造方法

        // 不带索引

        // Set集合 所以 没有 重复

        TreeSet<Integer> ts = new TreeSet<Integer>();

        ts.add(10);
        ts.add(40);
        ts.add(30);
        ts.add(50);
        ts.add(20);

        for (Integer i : ts) {
            System.out.println(i);
        }
    }
}
