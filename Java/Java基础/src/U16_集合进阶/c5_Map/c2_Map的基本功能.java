package U16_集合进阶.c5_Map;

import java.util.HashMap;
import java.util.Map;

public class c2_Map的基本功能 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();

        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");

        System.out.println(map);

        // 1.长度
        System.out.println("map集合长度：" + map.size());

        // 2.判断 集合 是否包含指定的 键 或 值
        System.out.println("是否含有 2 键：" + map.containsKey("2"));
        System.out.println("是否含有 2 值：" + map.containsValue("2"));

        // 3.判断 根据 键 删除 键值对 元素
        System.out.println("map.remove::" + map.remove("2"));

        System.out.println(map);

        // 4.清空 map集合
        map.clear();

        // 5.判断 是否 为空
        System.out.println("集合是否为空：" + map.isEmpty());

        System.out.println(map);

    }
}
