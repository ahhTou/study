package U16_集合进阶.c3_Set.c2_TreeSet.c3_比较器排序Comparator;

import U16_集合进阶.c3_Set.c2_TreeSet.c2_自然排序Comparable.Student;

import java.util.Comparator;
import java.util.TreeSet;

public class 比较器排序Comparator {
    public static void main(String[] args) {
        TreeSet<Student> ts = new TreeSet<Student>(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int num = s1.getAge() - s2.getAge();
                int num2 = num == 0 ? s1.getName().compareTo(s2.getName()) : num;
                return num2;
            }
        });

        Student s1 = new Student("ahhTou1", 35);
        Student s2 = new Student("ahhTou2", 32);
        Student s3 = new Student("ahhTou3", 33);
        Student s4 = new Student("ahhTou4", 34);
        Student s5 = new Student("ahhTou5", 34);

        ts.add(s1);
        ts.add(s2);
        ts.add(s3);
        ts.add(s4);
        ts.add(s5);

        for (Student s : ts) {
            System.out.println(s.getName() + ',' + s.getAge());
        }

    }
}
