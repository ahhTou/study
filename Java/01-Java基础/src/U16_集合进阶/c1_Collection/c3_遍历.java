package U16_集合进阶.c1_Collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class c3_遍历 {
    public static void main(String[] args) {
        Collection<String> c = new ArrayList<String>();

        c.add("hello");
        c.add("world");
        c.add("Java");

        Iterator<String> it = c.iterator(); // return new Itr();

//        System.out.println(it.next());
//        System.out.println(it.next());
//        System.out.println(it.next());
//        System.out.println(it.next()); // NoSuchElementException

//        if (it.hasNext()) System.out.println(it.next());
//        if (it.hasNext()) System.out.println(it.next());
//        if (it.hasNext()) System.out.println(it.next());
//        if (it.hasNext()) System.out.println(it.next());

        while (it.hasNext()){
            String s =it.next();
            System.out.println(s);
        }

    }
}
