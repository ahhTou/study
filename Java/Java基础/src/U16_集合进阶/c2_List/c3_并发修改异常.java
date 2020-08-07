package U16_集合进阶.c2_List;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class c3_并发修改异常 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();

        list.add("hello");
        list.add("world");
        list.add("Java");
        list.add("hello");

        Iterator<String> it = list.iterator();
//        while (it.hasNext()) {
//            String s = it.next();
//            if (s.equals("world")) {
//                list.add("javaEE"); //ConcurrentModificationException
//            }
//        }

        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.equals("world")) {
                list.add("javaEE"); //ConcurrentModificationException
            }
        }
        System.out.println(list);
    }
}
