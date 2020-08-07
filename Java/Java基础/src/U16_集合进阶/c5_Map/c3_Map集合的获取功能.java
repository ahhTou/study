package U16_集合进阶.c5_Map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class c3_Map集合的获取功能 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();

        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");

        // 1. get() 根据键 获取 值
        System.out.println("map.get(1): " + map.get("1"));
        System.out.println("map.get(4): " + map.get("4"));

        // 2.keySet() 获取 所有 键 的集合
        Set<String> keySet = map.keySet();
        System.out.print("keySet:");
        for (String key : keySet) {
            System.out.print(key + " ");
        }

        // 3.values() 获取所有值的集合
        Collection<String> values = map.values();
        System.out.println();
        System.out.print("map.values:");
        for (String value : values) {
            System.out.print(value + " ");
        }

    }
}
