package U16_集合进阶.c4_泛型;

import java.util.ArrayList;
import java.util.List;

public class c5_类型通配符 {
    public static void main(String[] args) {
        List<?> list1 = new ArrayList<Object>();
        List<?> list2 = new ArrayList<Number>();
        List<?> list3 = new ArrayList<Integer>();

        System.out.println("-------------------");

        // 上限是 Number ，不能new Object
//        List<? extends Number> list4 = new ArrayList<Object>();

        List<? extends Number> list4 = new ArrayList<Integer>();

        // 下限
        List<? super Number> list5 = new ArrayList<Object>();
        List<? super Number> list6 = new ArrayList<Number>();
//        List<? super Number> list7 = new ArrayList<Integer>();

    }
}
