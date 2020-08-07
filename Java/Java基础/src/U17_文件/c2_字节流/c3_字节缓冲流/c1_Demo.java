package U17_文件.c2_字节流.c3_字节缓冲流;

import java.io.*;

public class c1_Demo {
    public static void main(String[] args) throws IOException {
//        复制视频 效率对比:
//        1. 字节缓冲流 一次 读写一个字节数组 60ms
//        2. 基本字节流 一次 读写一个字节数组 107ms
//        3. 字节缓冲流 一次 读写一个字节 460ms
//        4. 基本字节流 一次 读写一个字节 64565ms



//        写数据
//        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("./src/U17_文件/c2_字节流/c0_临时文件/fos.txt"));
//
//        bos.write("hello\r\n".getBytes());
//        bos.write("world\r\n".getBytes());
//
//        bos.close();

//        读数据
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("./src/U17_文件/c2_字节流/c0_临时文件/fos.txt"));

//        一次读取一个字节数据
//        int by;
//        while ((by = bis.read()) != -1){
//            System.out.print((char) by);
//        }

//        一次读取一个字节数组数据
        byte[] bys = new byte[1024];
        int len;
        while ((len = bis.read(bys)) != -1) {
            System.out.println(new String(bys, 0, len));
        }

        bis.close();
    }
}
