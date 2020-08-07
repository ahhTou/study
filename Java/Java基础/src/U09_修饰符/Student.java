package U09_修饰符;

public class Student {
    public String name;
    public static int age;

    // 共享
    public static String university;

    public void show() {
        // 非静态 可以访问 静态
        System.out.println(name + "," + age + "," + university);
    }

    public static void show2() {
        // 静态方法 只能访问 静态成员
        System.out.println(age);
//        System.out.println(name + "," + age + "," + university);
    }

}
