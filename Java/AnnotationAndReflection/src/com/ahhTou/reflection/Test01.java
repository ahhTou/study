package com.ahhTou.reflection;

// 什么叫反射
public class Test01 {
    public static void main(String[] args) throws ClassNotFoundException {
        // 通过反射获取类的class
        Class<?> c1 = Class.forName("com.ahhTou.reflection.User");
        System.out.println(c1);


        // 一个类再内存中只有一个class对象。
        // 一个类被加载后，类的整个结构都会被封装在Class对象中。
        Class<?> c2 = Class.forName("com.ahhTou.reflection.User");
        Class<?> c3 = Class.forName("com.ahhTou.reflection.User");
        Class<?> c4 = Class.forName("com.ahhTou.reflection.User");
        System.out.println(c2.hashCode());
        System.out.println(c3.hashCode());
        System.out.println(c4.hashCode());
    }
}

// 实体类

class User {
    private String name;
    private int id;
    private int age;


    public User() {
    }

    public User(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
