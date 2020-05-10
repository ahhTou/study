package U22_方法引用_Lambda扩展.c6_引用构造器;

public class StudentDemo {
    public static void main(String[] args) {

        useStudentBuilder((name, age) -> new Student(name, age));

        useStudentBuilder(Student::new);

        // lambda表达式被调用时，他的形式参数全部被构造函数作为参数
    }

    private static void useStudentBuilder(StudentBuilder sb) {
        Student s = sb.build("ahhTou", 20);
        System.out.println(s.getName() + "," + s.getAge());
    }
}
