package U17_文件.c3_字符流.c4_综合实例.c3_多级文件夹复制;

import java.io.*;

public class CopyFoldersDemo {
    public static void main(String[] args) throws IOException {
        File srcFile = new File(
                "./src/U17_文件/c3_字符流/c4_综合实例/c1_成绩排序录入"
        );

        // 创建目的地目录的file对象
        File destFile = new File(
                "./src/U17_文件/c3_字符流/c4_综合实例/c3_多级文件夹复制/Copy"
        );

        copyFolder(srcFile, destFile);


    }

    private static void copyFolder(File srcFile, File destFile) throws IOException {
        if (srcFile.isDirectory()) {
            String srcFileName = srcFile.getName();
            File newFolder = new File(destFile, srcFileName);
            if (!newFolder.exists()) {
                System.out.println(
                        "目标文件夹不存在，已经成功创建？ == "
                                + newFolder.mkdirs());
            }
            File[] listFiles = srcFile.listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    copyFolder(file, newFolder);
                }
            }

        } else {
            String srcFileName = srcFile.getName();
            File newFile = new File(destFile, srcFileName);
            copyFile(srcFile, newFile);
        }
    }

    private static void copyFile(File srcFile, File destFile) throws IOException {

        BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(srcFile)
        );
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(destFile)
        );

        byte[] bytes = new byte[1024];
        int len;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }
        bis.close();
        bos.close();

    }
}
