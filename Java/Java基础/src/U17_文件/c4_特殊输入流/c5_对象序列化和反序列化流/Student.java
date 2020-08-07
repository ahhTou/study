package U17_文件.c4_特殊输入流.c5_对象序列化和反序列化流;

import java.io.Serializable;

// 序列化必须实现这个方法 ， 仅是标识接口
public class Student implements Serializable {
    // 解决序列化出错
    private static final long serialVersionUID = 1l;
    private String name;
    private int age;
    // 不参与序列化
    private transient int sss;

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
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
