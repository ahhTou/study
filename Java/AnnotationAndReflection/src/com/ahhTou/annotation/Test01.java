package com.ahhTou.annotation;

import java.util.ArrayList;
import java.util.List;

// 什么是注解 （不是必须，但是有些程序存在）
public class Test01 {

    // @Override 重写的注解 （检查和约束）
    @Override
    public String toString() {

        return "Test01{}";

    }

    // 已过时的，但是可以使用，但是存在更好的方法
    @Deprecated
    public static void test() {
        System.out.println("Deprecated");
    }

    // 镇压警告， 可以放在类和方法上面
    @SuppressWarnings("all")
    public void test02() {
        List list = new ArrayList();
    }

    public static void main(String[] args) {
        test();
    }
}
