package U17_文件.c4_特殊输入流.c6_Properties;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class c3_与IO流结合 {
    public static void main(String[] args) throws IOException {
        // 1.保存
        myStore();

        // 2.加载
        myLoad();
    }

    private static void myLoad() throws IOException {
        Properties prop = new Properties();

        FileReader fr = new FileReader(
                "./src/U17_文件/c4_特殊输入流/c0.txt"
        );

        prop.load(fr);
        fr.close();

        System.out.println(prop);
    }

    private static void myStore() throws IOException {
        Properties prop = new Properties();

        prop.setProperty("ahhTou1", "小王1");
        prop.setProperty("ahhTou2", "小王2");
        prop.setProperty("ahhTou3", "小王3");

        FileWriter fw = new FileWriter(
                "./src/U17_文件/c4_特殊输入流/c0.txt"
        );

        prop.store(fw, null);
        fw.close();
    }
}
