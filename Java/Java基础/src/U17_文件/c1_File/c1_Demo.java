package U17_文件.c1_File;

import java.io.File;

public class c1_Demo {
    public static void main(String[] args) {
        // 通过将给定的路径名字符串转换为抽象路径名来创建新的File实例。
        File f1 = new File("./java.txt");
        System.out.println(f1);

        File f2 = new File("./", "java.txt");
        System.out.println(f2);

        File f3 = new File("./");
        File f4 = new File(f3, "java.txt");
        System.out.println(f4);
    }
}
