package U14_常用Api.c3_Object.equals;

public class ObjectDemo {
    public static void main(String[] args) {
        Student s = new Student();
        s.setAge(30);
        s.setName("ahhTou");

        Student s2 = new Student();
        s2.setAge(30);
        s2.setName("ahhTou");

        System.out.println(s.equals(s2));



    }
}
