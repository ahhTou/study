package U17_文件.c3_字符流.c4_综合实例.c2_单级文件夹复制;

import java.io.*;

public class CopyFolderDemo {
    public static void main(String[] args) throws IOException {
        File srcFolder = new File(
                "./src/U17_文件/c3_字符流/c4_综合实例/c1_成绩排序录入"
        );
        // 得到源目录file对象的名称
        String srcFolderName = srcFolder.getName();

        // 创建目的地目录的file对象
        File destFolder = new File(
                "./src/U17_文件/c3_字符流/c4_综合实例/c2_单级文件夹复制/Copy"
        );

        if (!destFolder.exists()) {
            System.out.println("文件夹创建成功？==" + destFolder.mkdirs());
        } else {
            System.out.println("文件夹已存在");
        }

        //获得源目录下所有文件的file数组
        File[] listFiles = srcFolder.listFiles();

        if (listFiles != null) {
            for (File srcFile : listFiles) {
                // 数据源文件
                String srcFileName = srcFile.getName();
                File destFile = new File(destFolder, srcFileName);
                // 复制文件
                copyFile(srcFile, destFile);
            }
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
