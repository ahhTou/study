package com.ahhTou.reflection;

import java.lang.annotation.*;
import java.lang.reflect.Field;

// 练习反射操作注解
public class Test11 {

    public static void main(String[] args) throws Exception {
        Class<?> c1 = Class.forName("com.ahhTou.reflection.Student2");

        // 通过反射获取注解
        Annotation[] annotations = c1.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
        // 获取注解的value的值
        TableAhhTou tableAhhTou = c1.getAnnotation(TableAhhTou.class);
        String value = tableAhhTou.value();
        System.out.println(value);

        // 获得类指定的注解
        Field f = c1.getDeclaredField("name");
        FieldAhhTou annotation = f.getAnnotation(FieldAhhTou.class);
        System.out.println(annotation.columnName());
        System.out.println(annotation.type());
        System.out.println(annotation.length());
    }

}

@TableAhhTou("db_student")
class Student2 {
    @FieldAhhTou(columnName = "db_id", type = "int", length = 10)
    private int id;
    @FieldAhhTou(columnName = "db_age", type = "int", length = 10)
    private int age;
    @FieldAhhTou(columnName = "db_name", type = "varchar", length = 3)
    private String name;

    public Student2(int id, int age, String name) {
        this.id = id;
        this.age = age;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student2() {
    }

    @Override
    public String toString() {
        return "Test11{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}


// 类名的注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface TableAhhTou {
    String value();
}

// 属性的注解
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FieldAhhTou {
    String columnName();

    String type();

    int length();
}