package com.ahhTou.reflection;

import java.lang.reflect.Method;

public class Test09 {

    // 普通方式调用
    public static void test01() {
        User user = new User();
        // 反射方法调用
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            user.getName();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("普通方式执行10亿次" + (endTime - startTime) + "ms");


    }

    // 反射方法调用，开启检测
    public static void test02() throws Exception {
        User user = new User();
        Class c1 = user.getClass();
        Method getName = c1.getDeclaredMethod("getName", null);
        // 反射方法调用
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(user, null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("开启检测 反射方式执行10亿次" + (endTime - startTime) + "ms");

    }

    // 反射方式调用，关闭检测
    public static void test03() throws Exception {
        User user = new User();
        Class c1 = user.getClass();
        Method getName = c1.getDeclaredMethod("getName", null);
        getName.setAccessible(true);
        // 反射方法调用
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(user, null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("关闭检测 反射方式执行10亿次" + (endTime - startTime) + "ms");

    }

    public static void main(String[] args) throws Exception {
        test01();
        test02();
        test03();
    }
}
