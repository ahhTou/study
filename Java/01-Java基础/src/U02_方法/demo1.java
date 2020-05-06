package U02_方法;

public class demo1 {
    // public static 修饰符
    // void 返回值类型
    public static void main(String[] args) {
        say();
        sayWhat("every day");
        // "every day" 为实参
        System.out.println(whatYouSay());
    }

    // 方法不能嵌套
    public static void say() {
        System.out.println("fuck");
        return; //可以隐藏
    }

    public static void sayWhat(String str) {
        // str 为形参
        System.out.println(str);
    }

    public static String whatYouSay() {
        return "I love you";
    }
}
