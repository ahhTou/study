package U16_集合进阶.c4_泛型;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class c1 {
    public static void main(String[] args) {

//        Collection c = new ArrayList();
        Collection<String> c = new ArrayList<String>();

        c.add("hello");
        c.add("world");
        c.add("java");
//        c.add(100); 泛型好处：避免了强制类型转换和提前异常

//        Iterator it = c.iterator();
        Iterator<String> it = c.iterator();
        while (it.hasNext()) {
//            Object obj = it.next();
//            System.out.println(obj);
            String s = it.next();
            System.out.println(s);
        }
    }
}
