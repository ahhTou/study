package U17_文件.c4_特殊输入流.c6_Properties;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class c2_特有方法 {
    public static void main(String[] args) {

        Properties prop = new Properties();

        prop.setProperty("ahhTou001", "小王001");
        prop.setProperty("ahhTou002", "小王002");
        prop.setProperty("ahhTou003", "小王003");


        System.out.println(prop.getProperty("ahhTou001"));

        Set<String> names = prop.stringPropertyNames();

        for (String key : names) {
            String value = prop.getProperty(key);
            System.out.println(key + "," + value);
        }

        System.out.println(prop);
    }
}
