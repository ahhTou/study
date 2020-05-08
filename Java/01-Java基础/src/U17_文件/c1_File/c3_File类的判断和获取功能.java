package U17_文件.c1_File;

import java.io.File;

public class c3_File类的判断和获取功能 {
    public static void main(String[] args) {
        // 创建一个File文件
        File f1 = new File("./src/U17_文件/c1_File/c0_临时文件/");
        System.out.println("是文档吗？ " + f1.isDirectory());
        System.out.println("是文件吗？" + f1.isFile());
        System.out.println("存在吗？" + f1.exists());

        System.out.println("");

        System.out.println("绝对路径名字符串：" + f1.getAbsoluteFile());
        System.out.println("路径名字符串：" + f1.getPath());
        System.out.println("路径名表示的文件或目录的名称: " + f1.getName());

        System.out.println("");

        String[] strArray = f1.list();
        for (String str : strArray) {
            System.out.println("该目录下的文件和文件夹：" + str);
        }

        System.out.println("");

        File[] fileArray = f1.listFiles();
        for (File file : fileArray) {
//            System.out.println(file);
//            System.out.println(file.getName());
            if (file.isFile()) {
                System.out.println("该目录的文件" + file.getName());
            }
        }

    }
}
