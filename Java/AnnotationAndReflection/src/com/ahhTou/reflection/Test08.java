package com.ahhTou.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test08 {

    public static void main(String[] args) throws Exception {
        // 获得Class对象
        Class<?> c1 = Class.forName("com.ahhTou.reflection.User");

        // 构造一个对象
        // 1. 类必须有一个无参构造器
        // 2. 类的构造器访问权限必须足够
        User user = (User) c1.newInstance(); // 本质调用了无参构造器
        System.out.println(user);

        // 通过构造器创建对象
        // Constructor<?> constructor = c1.getDeclaredConstructor(String.class, int.class, int.class);
        // User user2 = (User) constructor.newInstance("ahhTou", 1, 1);
        // System.out.println(ahhTou);

        // 通过反射调用方法
        User user3 = (User) c1.newInstance();
        Method setName = c1.getDeclaredMethod("setName", String.class);
        setName.invoke(user3, "ahhTou");
        System.out.println(user3);

        // 通过反射修改属性
        System.out.println("===========");
        User user4 = (User) c1.newInstance();
        Field name = c1.getDeclaredField("name");
        // name.set(user4, "ahhTouPro");

        name.setAccessible(true); //取消安全属性
        name.set(user4, "ahhTouPro");
        System.out.println(user4);
    }

}
