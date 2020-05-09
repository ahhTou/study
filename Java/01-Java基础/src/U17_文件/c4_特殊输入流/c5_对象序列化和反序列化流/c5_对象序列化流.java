package U17_文件.c4_特殊输入流.c5_对象序列化和反序列化流;

import java.io.*;

public class c5_对象序列化流 {
    public static void main(String[] args) throws IOException {
        // 1. 对象序列化流
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(
                        "./src/U17_文件/c4_特殊输入流/c0.txt")
        );

        Student s = new Student("ahhTou", 20);

        // 写入
        oos.writeObject(s); //java.io.NotSerializableException 需要一个接口
        oos.close();
    }
}
