package U17_文件.c3_字符流.c3_字符缓冲流;

import java.io.*;

public class c2_字符缓冲流的特有功能 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(
                new FileWriter("./src/U17_文件/c3_字符流/c0_临时文件/fos.txt")
        );
        for (int i = 0; i < 10; i++) {
            bw.write("hello!");
            bw.newLine();
            bw.flush();
        }
        bw.close();

        BufferedReader br = new BufferedReader(
                new FileReader("./src/U17_文件/c3_字符流/c0_临时文件/fos.txt")
        );
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line); // 不读换行符
        }

        br.close();

    }
}
