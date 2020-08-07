package U24_Stream流.c6_Stream流收集操作;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectDemo {
    public static void main(String[] args) {

        // 1.收集list
        List<String> list = new ArrayList<>();
        list.add("林青霞");
        list.add("张曼玉");
        list.add("王祖贤");
        list.add("柳岩");

        Stream<String> listStream = list.stream()
                .filter(s -> s.length() == 3);

        // 把 名字长度为3 的名字收集到list集合并遍历
        List<String> names = listStream.collect(Collectors.toList());
        names.forEach(System.out::println);

        // 2. 收集set
        Set<Integer> set = new HashSet<>();
        set.add(10);
        set.add(20);
        set.add(30);
        set.add(40);
        set.add(50);
        set.add(60);


        Stream<Integer> setStream = set.stream().filter(age -> age > 25);

        Set<Integer> ages = setStream.collect(Collectors.toSet());
        ages.forEach(System.out::println);

        // 收集
        String[] strArray = {"林青霞,30", "张曼玉,35", "王祖贤,33", "柳岩,25"};

        Stream<String> arrayStream = Stream.of(strArray)
                .filter(s -> Integer.parseInt(s.split(",")[1]) > 28);

        Map<String, Integer> map = arrayStream.collect(Collectors.toMap(
                s -> s.split(",")[0],
                s -> Integer.parseInt(s.split(",")[1])
        ));

        map.forEach((key,value)->{
            System.out.println(key + ',' + value);
        });
    }
}
