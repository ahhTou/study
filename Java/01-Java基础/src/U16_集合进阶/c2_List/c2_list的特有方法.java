package U16_集合进阶.c2_List;

import java.util.ArrayList;
import java.util.List;

public class c2_list的特有方法 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();

        list.add("hello");
        list.add("world");
        list.add("Java");
        list.add("hello");

        // 在指定位置插入
        list.add(2, "爷插进来了");

        System.out.println(list);

        list.remove(1); // 返回移除的元素

        System.out.println(list);

        list.set(1, "JavaEE"); // 返回修改的元素

        System.out.println("get的元素：" + list.get(1));

        System.out.println(list);

        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            System.out.println(s);
        }
    }
}
