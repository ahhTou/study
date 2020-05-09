package U17_文件.c4_特殊输入流;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class c1_标准输入流_Scanner的由来 {
    public static void main(String[] args) throws IOException {


//        标准输入流。通常该流对应于键盘输入或由主机环境或用户指定的另一-个输入源
//        InputStream is = System.in;
//        int by;
//        while ((by = is.read()) != -1) {
//            System.out.print((char) by);
//        }

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        String line = br.readLine();

        System.out.println("您输入的字符串是： " + line);
//        Scanner sc = new Scanner(System.in);

        // java自己提供的
        Scanner sc = new Scanner(System.in);

    }
}
