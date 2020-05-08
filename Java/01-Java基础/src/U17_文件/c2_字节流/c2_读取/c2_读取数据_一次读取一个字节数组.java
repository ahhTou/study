package U17_文件.c2_字节流.c2_读取;

import java.io.FileInputStream;
import java.io.IOException;

public class c2_读取数据_一次读取一个字节数组 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("./src/U17_文件/c2_字节流/c0_临时文件/fos.txt");

        // 同样支持图片
        byte[] bys = new byte[1024];
        int len;
        while ((len = fis.read(bys)) != -1) {
            System.out.print(new String(bys, 0, len));
        }

        fis.close();
    }
}


