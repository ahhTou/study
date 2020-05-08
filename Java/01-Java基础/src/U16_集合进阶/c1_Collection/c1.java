package U16_集合进阶.c1_Collection;

import java.util.ArrayList;
import java.util.Collection;

public class c1 {
    public static void main(String[] args) {

        // 创建Collection集合的对象
        Collection<String> c = new ArrayList<String>();

        // 添加
        c.add("hello"); // 返回值为boolean
        c.add("world");
        c.add("Java");

        System.out.println(c); // 重写了 toString方法


    }
}
