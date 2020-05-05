package com.ahhtou05.learnApi;

public class StringDemo2 {
    public static void main(String[] args) {
        // String 对象的特点
        // 只要内容相等只申请一个空间

        // 使用 == 作比较
        // 1.基本类型 比较的数据值是否相同
        // 2.引用类型 比较的地址值是否相同

        // equals()

        char[] chs = {'a', 'b', 'c'};
        // 构造
        String s1 = new String(chs);
        String s2 = new String(chs);

        String s3 = "abc";
        String s4 = "abc";

        // 比较地址
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s3 == s4);

        System.out.println("==========");

        // 比较内容
        System.out.println(s1.equals(s2));
        System.out.println(s1.equals(s3));
        System.out.println(s3.equals(s4));


    }
}
