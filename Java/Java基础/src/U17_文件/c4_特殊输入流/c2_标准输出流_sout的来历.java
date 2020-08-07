package U17_文件.c4_特殊输入流;

import java.io.PrintStream;

public class c2_标准输出流_sout的来历 {
    public static void main(String[] args) {
        PrintStream ps = System.out;

        ps.print("hello");
        ps.print("hello");
        ps.println(100);

        //System.out 的本质是一个字节输出流
        System.out.println(); // 可以没有参数
        System.out.print(""); // 必须有参数
    }
}
