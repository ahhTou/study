package U17_文件.c3_字符流.c2_写入和读取;

import java.io.*;

public class c1_写入的几种方法 {
    public static void main(String[] args) throws IOException {
        OutputStreamWriter ows =
                new OutputStreamWriter(
                        new FileOutputStream(
                                "./src/U17_文件/c3_字符流/c0_临时文件/fos.txt")
                        , "GBK");
//        1.写一个字符
        ows.write(97);
        ows.flush(); // 刷新，字符流是有缓存的，刷新后依然可以继续写入
        ows.write(98);
        ows.flush();
        ows.write(99);
//        2.写一个字符数组
        char[] chs = {'a', 'b', 'c', 'd', 'e'};
        ows.write(chs, 0, chs.length);

        ows.write(chs, 1, 3);

//        3.写一个字符串
        ows.write("abcdeffffff");
        ows.write("abcdeffffff", 1, 3);


        ows.close(); // 先刷新，后释放
    }
}
