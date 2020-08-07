package U17_文件.c1_File;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class c4_删除文件 {
    // 1.需要先删除文件 再 删除 目录！！！
    public static void main(String[] args) throws IOException {
        
        File f1 = new File("./src/U17_文件/c1_File/c0_临时文件/删除我.txt");
        System.out.println("创建文件是否成功：" + f1.createNewFile());

        Scanner sc = new Scanner(System.in);
        System.out.print("是否删除 文件?Y/N");
        String i = sc.next();
        if (i.equals("Y") || i.equals("y")) {
            System.out.println("删除成功? ==" + f1.delete());
        }

        File f2 = new File("./src/U17_文件/c1_File/c0_临时文件/删除我");
        System.out.println("创建文件夹是否成功？==" + f2.mkdir());

        System.out.print("是否删除 文件夹?Y/N");
        i = sc.next();
        if (i.equals("Y") || i.equals("y")) {
            System.out.println("删除成功? ==" + f2.delete());
        }


    }
}
