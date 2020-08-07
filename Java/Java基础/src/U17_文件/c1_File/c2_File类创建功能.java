package U17_文件.c1_File;

import java.io.File;
import java.io.IOException;

public class c2_File类创建功能 {
    public static void main(String[] args) throws IOException {
        // 创建一个文件 文件不存在，就创建返回true。存在就不创建并返回false

        File f1 = new File("./src/U17_文件/c1_File/c0_临时文件/c0_临时创建文件.txt");
        System.out.println("创建文件：" + f1.createNewFile());


        // 创建一个目录 目录不存在，就创建返回true。存在就不创建并返回false
        File f2 = new File("./src/U17_文件/c1_File/c0_临时文件/c0_临时创建的文件夹");
        System.out.println("创建文件夹：" + f2.mkdir());

        // 创建一个多级目录
        File f3 = new File("./src/U17_文件/c1_File/c0_临时文件/c0_临时创建的多级文件夹/文件夹");
        System.out.println("创建多级文件夹：" + f3.mkdirs());

        // 创建一个文件
        File f4 = new File("./src/U17_文件/c1_File/c0_临时文件/c0_临时创建文件2.txt");
        System.out.println("创建文件：" + f4.createNewFile());


    }
}
