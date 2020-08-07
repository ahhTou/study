package U17_文件.c2_字节流.c1_写入;

import java.io.FileOutputStream;
import java.io.IOException;

public class c1_Demo {
    // InputStream 抽象类 所有输入流的 超类
    // OutputStream 抽象类 所有输出流的 超类
    public static void main(String[] args) throws IOException {
        // 创建 字节输出流对象
        FileOutputStream fos = new FileOutputStream("./src/U17_文件/c2_字节流/c0_临时文件/fos.txt");

        // 1. 调用了系统功能创建了文件
        // 2. 创建了字节流流输出流对象
        // 3. 让直接输出流指向创建好的文件

        // void write (int b) :讲指定的直接写入文件输出流
        fos.write(97);

        fos.close();// 关闭了文件输出流 并 释放了于此 流现骨干连的任何系统资源

    }
}
