package U17_文件.c3_字符流;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class c5_异常处理 {
    public static void main(String[] args) {

    }

    // 1.抛出，未能解决问题
    private static void method1() throws IOException {
        FileReader fr = new FileReader("fr.txt");
        FileWriter fw = new FileWriter("fw.txt");
        fw.close();
        fr.close();
    }

    // try_catch_finally
    private static void method2() {
        FileReader fr = null;
        FileWriter fw = null;
        try {

            fr = new FileReader("fr.txt");
            fw = new FileWriter("fw.txt");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // JDK7 改进方案
    private static void method3() {
        try (FileReader fr = new FileReader("fr.txt");
             FileWriter fw = new FileWriter("fw.txt");
        ) {
            fw.write("123");
            fr.read();
            // 自动释放
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    JDK9的改进方案
//    private static void method1() throws IOException {
//        FileReader fr = new FileReader("fr.txt");
//        FileWriter fw = new FileWriter("fw.txt");
//        try (fr; fw) {
//            fw.close();
//            fr.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
