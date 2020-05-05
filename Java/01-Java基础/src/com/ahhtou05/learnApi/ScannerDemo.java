package com.ahhtou05.learnApi;

import java.util.Scanner;

public class ScannerDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串数据：");
        String line = sc.nextLine(); // ctrl + alt + v

        System.out.println("你输入的数据是：" + line);

    }
}
