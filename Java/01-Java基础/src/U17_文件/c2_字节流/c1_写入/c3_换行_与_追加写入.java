package U17_文件.c2_字节流.c1_写入;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class c3_换行_与_追加写入 {
    public static void main(String[] args) throws IOException {
//        追加写入
//        创建 字节输出流 对象 FileOutputStream(String name, boolean append)
        FileOutputStream fos = new FileOutputStream("./src/U17_文件/c2_字节流/c0_临时文件/fos.txt", true);


//        换行
        for (int i = 0; i < 10; i++) {
            fos.write("hello".getBytes());
            fos.write("\r\n".getBytes()); // windows 识别的是 \r\n linux 是 \n mac 是 \r
        }

//       释放资源
        fos.close();
    }
}
