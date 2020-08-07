package U14_常用Api.c3_Object.equals;

import java.util.Objects;

public class Student {
    private String name;
    private int age;

    public Student() {

    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        // 如果地址相同， 返回true
        if (this == o) return true;

        // 1.判断参数是否为 null ，为null 直接返回 false
        // 2.判断两个对象是否来自同一个类 ， 不为一个类返回 false
        if (o == null || getClass() != o.getClass()) return false;

        // 向下转型
        Student student = (Student) o;

        // 比较姓名或者年龄是否相同
        return age == student.age &&
                Objects.equals(name, student.name);
    }
}
