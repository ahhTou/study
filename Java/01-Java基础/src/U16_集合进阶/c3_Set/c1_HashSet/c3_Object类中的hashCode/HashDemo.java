package U16_集合进阶.c3_Set.c1_HashSet.c3_Object类中的hashCode;

public class HashDemo {
    public static void main(String[] args) {
        Student s1 = new Student("ahhTou", 30);

        // 同一个对象多次调用的哈希值是相同的
        System.out.println(s1.hashCode()); //1163157884
        System.out.println(s1.hashCode()); //1163157884

        Student s2 = new Student("ahhTou", 30);

        // 默认情况下， 不同对象的哈希值是不相同的
        // 通过方法重写，可以实现不同对象的哈希值是相同的
        System.out.println(s2.hashCode()); //1956725890

        // 不同字符串的hash值是不同的，同一字符串的是相同的
        System.out.println("hello".hashCode());
        System.out.println("world".hashCode());
        System.out.println("java".hashCode());

        //
        System.out.println("重地".hashCode());
        System.out.println("通话".hashCode());



    }
}
