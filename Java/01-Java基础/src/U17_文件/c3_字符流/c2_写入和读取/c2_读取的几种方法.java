package U17_文件.c3_字符流.c2_写入和读取;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class c2_读取的几种方法 {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream("./src/U17_文件/c3_字符流/c0_临时文件/fos.txt"));

        // 1.一次读一个字符数据
        int ch;
        while ((ch = isr.read()) != -1) {
            System.out.print((char) ch);
        }

        isr = new InputStreamReader(new FileInputStream("./src/U17_文件/c3_字符流/c0_临时文件/fos.txt"));

        // 2.一次读一个字符数组数据
        System.out.println();
        System.out.println("============");

        char[] chs = new char[1024];
        int len;
        while ((len = isr.read(chs)) != -1) {

            System.out.println(new String(chs, 0, len));
        }


        isr.close();
    }
}
