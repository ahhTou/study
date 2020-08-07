package U22_方法引用_Lambda扩展.c5_引用类的实例方法;

public class MyStringDemo {
    public static void main(String[] args) {
        useMyString((s, x, y) -> s.substring(x, y));

        useMyString(String::substring);

        // Lambda表达式被类的实力方法替代的时候
        // 第一个方法作为调用者
        // 后面的参数全部传递给该方法做参数

    }

    private static void useMyString(MyString my) {
        String s = my.mySubString("hello", 2, 5);
        System.out.println(s);
    }
}
