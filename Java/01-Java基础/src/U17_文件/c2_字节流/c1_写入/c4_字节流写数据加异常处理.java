package U17_文件.c2_字节流.c1_写入;

import java.io.FileOutputStream;
import java.io.IOException;

public class c4_字节流写数据加异常处理 {
    public static void main(String[] args) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("./src/U17_文件/c2_字节流/c0_临时文件/fos.txt");
            fos.write("hello".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null){ // 防止空指针异常
                try {
                    fos.close(); // 保证资源一定被释放
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
