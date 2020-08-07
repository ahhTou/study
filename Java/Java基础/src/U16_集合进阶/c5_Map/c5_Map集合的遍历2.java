package U16_集合进阶.c5_Map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class c5_Map集合的遍历2 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();

        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");

        Set<Map.Entry<String, String>> entries = map.entrySet();

        for (Map.Entry<String, String> me : entries) {
            String key = me.getKey();
            String value = me.getKey();
            System.out.println(key + ',' + value);
        }

    }
}
