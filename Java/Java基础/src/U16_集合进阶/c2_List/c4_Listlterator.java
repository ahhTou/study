package U16_集合进阶.c2_List;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class c4_Listlterator {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();

        list.add("hello");
        list.add("world");
        list.add("Java");

        ListIterator<String> lit = list.listIterator();
        while (lit.hasNext()){
            String s = lit.next();
            if(s.equals("world")){
                // try_catch解决并发异常
                lit.add("java");
            }
        }


//        ListIterator<String> lit = list.listIterator();
//        while (lit.hasNext()) {
//            String s = lit.next();
//            System.out.println(s);
//        }
//        System.out.println("-----------");
//
//        while (lit.hasPrevious()) {
//            String s = lit.previous();
//            System.out.println(s);
//        }

    }
}
