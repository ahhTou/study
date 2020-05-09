package U17_文件.c4_特殊输入流.c6_Properties;

import java.util.Properties;
import java.util.Set;

public class c1_ {
    public static void main(String[] args) {
        // 不支持泛型
        Properties prop = new Properties(); // 作为Map集合使用
        prop.put("ahhTou1", "小王1");
        prop.put("ahhTou2", "小王2");
        prop.put("ahhTou3", "小王3");

        Set<Object> keySet = prop.keySet();

        for (Object key : keySet) {
            Object value = prop.get(key);
            System.out.println(key + "," + value);
        }

    }
}
