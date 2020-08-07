package U17_文件.c3_字符流.c1_编码和解码;

import java.io.*;

public class c2_字符流中的编码和解码问题 {
    public static void main(String[] args) throws IOException {
        //默认UTF-8
        OutputStreamWriter ows =
                new OutputStreamWriter(
                        new FileOutputStream(
                                "./src/U17_文件/c2_字节流/c0_临时文件/fos.txt")
                        , "GBK");
        ows.write("中国");
        ows.close();

        InputStreamReader isr =
                new InputStreamReader(
                        new FileInputStream(
                                "./src/U17_文件/c2_字节流/c0_临时文件/fos.txt")
                        , "GBK");
//        1. 一次读取一个字符数据
//        int ch;
//        while ((ch = isr.read()) != -1) {
//            System.out.print((char) ch);
//        }
//        isr.close();

        // 2.一次读取
    }
}
