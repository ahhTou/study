package U23_函数式接口_Lambda扩展.c4_常见的函数式接口.c3_Predicate;

import java.util.ArrayList;
import java.util.function.Predicate;

public class c3_练习 {
    public static void main(String[] args) {

        String[] strArray = {"林青霞,30", "柳岩,34", "张曼玉,35", "貂蝉,31", "王祖蓝,33"};

        ArrayList<String> a = myFilter(
                strArray,
                s -> s.split(",")[0].length() > 2,
                s -> Integer.parseInt(s.split(",")[1]) > 33
        );

        for (String str : a) {
            System.out.println(str);
        }

    }

    private static ArrayList<String> myFilter(
            String[] strArray,
            Predicate<String> pre1,
            Predicate<String> pre2
    ) {
        ArrayList<String> array = new ArrayList<String>();
        // 遍历数组
        for (String str : strArray) {
            if (pre1.and(pre2).test(str)) {
                array.add(str);
            }
        }
        return array;
    }
}
