package U17_文件.c4_特殊输入流;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class c4_字符打印流 {
    public static void main(String[] args) throws IOException {
//        PrintWriter pw = new PrintWriter(
//                "./src/U17_文件/c4_特殊输入流/c0.txt");
//        pw.write("123");
//        pw.flush();
//        pw.write("123");
//
//        pw.println("123");
//        pw.println("123");
//        pw.println("123");
//        pw.flush();

        // 自动换行
        PrintWriter pw = new PrintWriter(
                new FileWriter(
                        "./src/U17_文件/c4_特殊输入流/c0.txt"),
                true);
        pw.println("hello");

        pw.close();


    }
}
