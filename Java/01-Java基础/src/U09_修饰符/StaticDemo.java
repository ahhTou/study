package U09_修饰符;

public class StaticDemo {
    public static void main(String[] args) {

        //静态类 是被共享的成员
        Student.university = "南阳师范学院";

        Student s1 = new Student();
        s1.name = "ahhTou";
        s1.age = 30;
//        s1.university = "南阳师范学院";
        s1.show();

        Student s2 = new Student();
        s2.name = "ahhTouPro";
        s1.age = 18;
//        s1.university = "清华大学";
        s2.show();
    }
}
