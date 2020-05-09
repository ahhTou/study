package U17_文件.c4_特殊输入流.c5_对象序列化和反序列化流;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class c6_对象反序列化流 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 1.构造
        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(
                        "./src/U17_文件/c4_特殊输入流/c0.txt"
                )
        );

        Object obj = ois.readObject();
        Student s = (Student) obj;
        System.out.println(s.toString());
        ois.close();
    }
}
