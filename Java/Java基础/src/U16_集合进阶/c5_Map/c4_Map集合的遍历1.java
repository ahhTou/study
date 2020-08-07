package U16_集合进阶.c5_Map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class c4_Map集合的遍历1 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();

        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");

        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            String value = map.get(key);
            System.out.println("键名为" + key + " 的值为 " + value);
        }

    }
}
