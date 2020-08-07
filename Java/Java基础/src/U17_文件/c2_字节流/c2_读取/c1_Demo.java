package U17_文件.c2_字节流.c2_读取;

import java.io.FileInputStream;
import java.io.IOException;

public class c1_Demo {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("./src/U17_文件/c2_字节流/c0_临时文件/fos.txt");

//        int by = fis.read();
//        System.out.println(by);
//        by = fis.read();
//        System.out.println(by);
//        by = fis.read();
//        System.out.println(by);
//        by = fis.read();
//        System.out.println(by);
//        by = fis.read();
//        System.out.println(by); // -1 是末尾、

//        while (by!= -1){
//            System.out.print((char)by);
//            by = fis.read();
//        }

//        改进 , 字节流读数据标准方法
        int by;
        while ((by=fis.read())!=-1){
            System.out.print((char)by);
        }

        fis.close();
    }
}
