package com.ahhtou04.learnPackage;

public class Student {
    String name;
    private int age;

    // 构造函数
    public Student() {
        System.out.println("你没有传入参数, 无参构造");
    }

    public Student(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println(name + "," + age);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0 || age > 120) {
            System.out.println("你输入的年龄有问题");
        } else {
            // this 指的是 成员变量
            this.age = age;
        }
    }
}
