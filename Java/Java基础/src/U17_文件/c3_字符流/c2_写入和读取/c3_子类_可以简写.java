package U17_文件.c3_字符流.c2_写入和读取;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class c3_子类_可以简写 {
    public static void main(String[] args) throws IOException {
        // 创建 输入和输出流 对象
        FileReader fr = new FileReader(
                "./src/U17_文件/c3_字符流/c2_写入和读取/c1_写入的几种方法.java");

        FileWriter fw = new FileWriter(
                "./src/U17_文件/c3_字符流/c2_写入和读取/c0_Copy.java");
//        单字符
//        int ch;
//        while ((ch = fr.read()) != -1) {
//            fw.write(ch);
//        }

//        字符数组
        char[] chs = new char[1024];
        int len;
        while ((len = fr.read(chs)) != -1) {
            fw.write(chs, 0, len);
        }

        // 释放资源
        fw.close();
        fr.close();
    }
}
