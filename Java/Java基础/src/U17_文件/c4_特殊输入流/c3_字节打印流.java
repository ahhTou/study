package U17_文件.c4_特殊输入流;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/*

    打印流 特点：
    只负责输出数据，不负责输出数据
    有自己的特有芳芳

    字节打印流


 */
public class c3_字节打印流 {
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream ps = new PrintStream(
                "./src/U17_文件/c4_特殊输入流/c0.txt");

        // 1.常规 父类芳芳
        ps.write(97);

        // 2.特有
        ps.print(97);
        ps.print(98);
        ps.println(98);
        ps.println(98);

        ps.close();
    }

}
