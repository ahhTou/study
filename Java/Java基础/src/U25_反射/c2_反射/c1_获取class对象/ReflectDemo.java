package U25_反射.c2_反射.c1_获取class对象;

public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException {

        // 通过类的class属性获取该类的class对象
        Class<Student> c1 = Student.class;
        System.out.println(c1);

        Class<Student> c2 = Student.class;
        System.out.println(".class ==" + (c1 == c2));

        // 通过该类的getClass方法
        Student s = new Student();
        Class<? extends Student> c3 = s.getClass();
        System.out.println(".class & getClass ==" + (c1 == c3));

        // 通过Class类的静态方法
        Class<?> c4 = Class.forName("U25_反射.c2_反射.c1_获取class对象.Student");
        System.out.println("forName & .class ==" + (c1 == c4));

    }
}
