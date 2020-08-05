package com.ahhTou.reflection;

public class Test02 {

    public static void main(String[] args) throws ClassNotFoundException {
        Person person = new Student();
        System.out.println("这个人是：" + person.name);

        // 方式1 通过对象获得
        Class<?> c1 = person.getClass();

        // 方式2 通过forName
        Class<?> c2 = Class.forName("com.ahhTou.reflection.Student");

        System.out.println(c1.hashCode());
        System.out.println(c2.hashCode());

        // 方式3 通过类名.class 获得
        Class<?> c3 = Student.class;

        // 方式4 通过类名的.class获得
        Class<Integer> c4 = Integer.TYPE;
        System.out.println(c4);

        // 获得父类的类型
        Class<?> superclass = c1.getSuperclass();
        System.out.println(superclass);
    }

}

class Person {
    String name;


    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Student extends Person {

    public Student() {
        this.name = "学生";
    }

}

class Teacher extends Person {

    public Teacher() {
        this.name = "老师";
    }

}