package com.ahhTou.reflection;

// 测试类什么时候会初始化
public class Test05 {

    static {
        System.out.println("main 类被加载");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        // 1. 主动引用
        // Son son = new Son();
        // 反射也会产生主动引用
        //Class.forName("com.ahhTou.reflection.Son");

        // 2. 被动引用
        // 不会引用产生类的引用方法
        // System.out.println(Son.b); // 子类没有被加载
        // Son[] array = new Son[5]; // 只是开辟了一个空间，并没有被加载

        System.out.println(Son.M); // M被放到常量池里，并没有被加载

    }

}

class Father {

    static int b = 2;

    static {
        System.out.println("父类被加载");
    }
}

class Son extends Father {
    static {
        System.out.println("子类被加载");
        m = 300;
    }

    static int m = 100;
    static final int M = 1;
}