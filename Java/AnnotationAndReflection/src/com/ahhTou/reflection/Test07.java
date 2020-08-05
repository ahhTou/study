package com.ahhTou.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test07 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        Class<?> c1 = Class.forName("com.ahhTou.reflection.User");

/*        User user = new User();
        c1 = user.getClass();*/

        // 得到类的名字
        System.out.println(c1.getName()); // 获得包名 + 类名
        System.out.println(c1.getSimpleName()); // 得到类名

        // 获得类的属性
        System.out.println("=============");
        Field[] field = c1.getFields(); // 只能找到public属性

        field = c1.getDeclaredFields(); // 能得到全部属性
        for (Field field1 : field) {
            System.out.println(field1);
        }

        // 获得指定属性的值
        Field name = null;
        // name = c1.getField("name"); // 报错
        name = c1.getDeclaredField("name");
        System.out.println(name);

        System.out.println("======");

        // 活得类的方法
        Method[] methods = c1.getMethods(); // 获得本类及其父类全部的public方法
        for (Method method : methods) {
            System.out.println("正常的 :" + method);
        }

        methods = c1.getDeclaredMethods(); // 获取本类的所有方法
        for (Method method : methods) {
            System.out.println("getDeclaredMethods :" + method);
        }

        // 获取指定的方法
        Method getName = c1.getMethod("getName", null);
        Method setName = c1.getMethod("setName", String.class);
        System.out.println(getName);
        System.out.println(setName);

        System.out.println("获得指定的构造器");
        System.out.println("===============");
        Constructor[] constructors = c1.getConstructors(); // public
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
        constructors = c1.getDeclaredConstructors();    // 本类的
        for (Constructor constructor : constructors) {
            System.out.println("    " + constructor);
        }

        // 获得指定的构造类
        Constructor<?> constructor = c1.getConstructor(String.class, int.class, int.class);
        Constructor<?> declaredConstructor = c1.getDeclaredConstructor(String.class, int.class, int.class);
        System.out.println("指定：" + constructor);
        System.out.println("指定declared：" + declaredConstructor);


    }


}
