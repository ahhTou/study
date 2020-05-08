package U16_集合进阶.c6_Collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class c1_Demo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();


        System.out.println(list);

        list.add(30);
        list.add(20);
        list.add(50);
        list.add(10);
        list.add(40);

        System.out.println(list);

        Collections.sort(list);

        System.out.println(list);

        Collections.shuffle(list);

        System.out.println("随机置换=>" + list);
    }
}
