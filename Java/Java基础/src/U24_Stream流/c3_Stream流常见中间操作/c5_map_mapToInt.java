package U24_Stream流.c3_Stream流常见中间操作;

import java.util.ArrayList;

public class c5_map_mapToInt {
    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();

        list.add("10");
        list.add("20");
        list.add("30");
        list.add("40");
        list.add("50");
        list.add("60");

        // map 转换
        list.stream()
                .map(Integer::parseInt)
                .forEach(System.out::println);

//        list.stream().mapToInt()
//                .forEach(System.out::println);
        // 可用于求和
        int result = list.stream().mapToInt(Integer::parseInt).sum();
        System.out.println(result);

    }
}
