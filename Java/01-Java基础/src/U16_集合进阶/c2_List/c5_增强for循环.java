package U16_集合进阶.c2_List;

import java.util.ArrayList;
import java.util.List;

public class c5_增强for循环 {
    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};
        for (int i : arr) {
            System.out.println(i);
        }
        System.out.println("______");

        String[] strArray = {"hello", "world", "java"};

        for (String i : strArray) {
            System.out.println(i);
        }
        System.out.println("______");

        List<String> list = new ArrayList<String>();
        list.add("hello");
        list.add("world");
        list.add("Java");

        for (String s : list) {
            System.out.println(s);
        }

        System.out.println("--------");

//        for (String s : list) {
//            if (s.equals("world")) {
//                list.add("123");
//                //抛出异常，说明原理是迭代器
//            }
//        }

    }
}
