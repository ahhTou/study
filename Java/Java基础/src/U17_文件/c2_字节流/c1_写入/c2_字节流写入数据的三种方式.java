package U17_文件.c2_字节流.c1_写入;

import java.io.FileOutputStream;
import java.io.IOException;

public class c2_字节流写入数据的三种方式 {
    public static void main(String[] args) throws IOException {
        // 相当于 做了一个 new File
//         创建方法 1
        FileOutputStream fos = new FileOutputStream("./src/U17_文件/c2_字节流/c0_临时文件/fos.txt");

//        创建方法 2
//        File file = new File("./src/U17_文件/c2_字节流/c0_临时文件/fos.txt");
//        FileOutputStream fos2 = new FileOutputStream(file);
//
//        创建方法 3
//        FileOutputStream fos3 = new FileOutputStream(new File("./src/U17_文件/c2_字节流/c0_临时文件/fos.txt"));

//        写入方法 1
//        fos.write(97);
//        fos.write(98);
//        fos.write(99);

//        写入方法 2
//        byte[] bys = {97, 98, 99};
//        fos.write(bys);

//        写入方法 3
        byte[] bys = "abcdef".getBytes();
//        fos.write(bys);
        fos.write(bys, 1, 3);


    }
}
