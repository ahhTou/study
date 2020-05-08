package U17_文件.c3_字符流.c3_字符缓冲流;

import java.io.*;

public class c1_Demo {
    public static void main(String[] args) throws IOException {
//        FileWriter fw = new FileWriter("./src/U17_文件/c3_字符流/c0_临时文件/fos.txt");
//        BufferedWriter bw = new BufferedWriter(fw);
        BufferedWriter bw = new BufferedWriter(
                new FileWriter("./src/U17_文件/c3_字符流/c0_临时文件/fos.txt")
        );
        bw.write("hello\r\n");
        bw.write("world\r\n");
        bw.close();

        BufferedReader br = new BufferedReader(
                new FileReader("./src/U17_文件/c3_字符流/c0_临时文件/fos.txt")
        );
        char[] chs = new char[1024];
        int len;
        while ((len = br.read(chs)) != -1) {
            System.out.println(new String(chs, 0, len));
        }
    }
}
