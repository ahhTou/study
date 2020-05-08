package U17_文件.c1_File.c5_递归;

import java.io.File;

public class c2_文件递归 {
    public static void main(String[] args) {
        File srcFile = new File("./src/U17_文件/c1_File/c0_临时文件");
        getAllFilePath(srcFile);
    }

    public static void getAllFilePath(File srcFile) {
        File[] fileArray = srcFile.listFiles();

        if (fileArray != null) {
            for (File file : fileArray) {
                if (file.isDirectory()) {
                    // 是路径 继续遍历
                    getAllFilePath(file);
                } else {
                    // 不是路径
                    System.out.println("得到了一个路径" + file.getAbsolutePath());
                }
            }
        } else {
            System.out.println("没有任何可以遍历的文件");
        }
    }
}
