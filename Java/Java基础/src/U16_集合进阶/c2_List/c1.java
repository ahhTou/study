package U16_集合进阶.c2_List;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class c1 {
    public static void main(String[] args) {
        // 创建集合对象 , list特点：有序、可重复
        List<String> list = new ArrayList<String>();

        list.add("hello");
        list.add("world");
        list.add("Java");
        list.add("hello");

        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            String s = it.next();
            System.out.println(s);
        }

    }
}
