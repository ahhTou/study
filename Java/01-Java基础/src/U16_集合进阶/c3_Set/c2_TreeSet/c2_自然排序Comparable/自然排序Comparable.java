package U16_集合进阶.c3_Set.c2_TreeSet.c2_自然排序Comparable;

import java.util.TreeSet;

public class 自然排序Comparable {
    public static void main(String[] args) {
        TreeSet<Student> ts = new TreeSet<Student>();

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
