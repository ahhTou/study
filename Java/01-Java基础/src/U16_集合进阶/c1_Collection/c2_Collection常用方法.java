package U16_集合进阶.c1_Collection;

import java.util.ArrayList;
import java.util.Collection;

public class c2_Collection常用方法 {
    public static void main(String[] args) {
        Collection<String> c = new ArrayList<String>();

        c.add("hello"); // 返回值 永远为 True
        c.add("world");
        c.add("Java");

        c.remove("Java"); // 返回值 True

        System.out.println("移除了Java" + c);

        System.out.println("判断是否含有 hello " + c.contains("hello"));

        System.out.println("判断是否为空：" + c.isEmpty());

        System.out.println("长度为：" + c.size());

        c.clear();

        System.out.println("被清空了：" + c);

        System.out.println("判断是否为空：" + c.isEmpty());
    }
}
