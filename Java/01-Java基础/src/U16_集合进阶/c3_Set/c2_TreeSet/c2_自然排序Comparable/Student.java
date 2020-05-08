package U16_集合进阶.c3_Set.c2_TreeSet.c2_自然排序Comparable;

public class Student implements Comparable<Student> {
    private String name;
    private int age;

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
    public int compareTo(Student s) {
//        return 0;
//        return -1; //0 重复元素、1 正序 -1 倒叙
        int num = this.age - s.age;
        int num2 = num ==0? this.name.compareTo(s.name):num;
        return num2;


    }
}
