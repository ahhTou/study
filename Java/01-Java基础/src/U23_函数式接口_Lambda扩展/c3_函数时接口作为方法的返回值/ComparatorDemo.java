package U23_函数式接口_Lambda扩展.c3_函数时接口作为方法的返回值;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ComparatorDemo {
    public static void main(String[] args) {

        ArrayList<String> array = new ArrayList<>();
        array.add("cccc");
        array.add("aa");
        array.add("b");
        array.add("ddd");

        System.out.println("排序前：" + array);

//        Collections.sort(array); // 自然排序

        Collections.sort(array, getComparator()); // 按照长度排

        array.sort(getComparator()); // 按照长度排
        System.out.println("排序后：" + array);
    }

    private static Comparator<String> getComparator() {

//        // 匿名内部类的方式
//        return new Comparator<String>() {
//            @Override
//            public int compare(String s1, String s2) {
//                return s1.length() - s2.length();
//            }
//        };

//        return (String s1, String s2) -> {
//            return s1.length() - s2.length();
//        };

        // 如果返回值是函数式接口，可以使用lambda表达式

        return (s1, s2) -> s1.length() - s2.length();
    }
}
