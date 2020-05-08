package U17_文件.c3_字符流.c1_编码和解码;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class c1_StringDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "中国";

        // 默认UTF-8
//        byte[] bytes = s.getBytes("GBK");
        // 编码
        byte[] bytes = s.getBytes();

        System.out.println(Arrays.toString(bytes));

        // 解码
        String s2 = new String(bytes);


        System.out.println(s2);
    }
}
