package U16_集合进阶.c5_Map;

import java.util.HashMap;
import java.util.Map;

public class c1_demo {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("ahhTou", "01");
        map.put("ahhTouPro", "02");
        map.put("ahhTouProMax", "03");
        map.put("ahhTouProMax", "04"); // 键 不能重复

        System.out.println(map);
    }
}
