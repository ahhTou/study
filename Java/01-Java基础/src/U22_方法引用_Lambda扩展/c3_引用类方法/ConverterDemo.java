package U22_方法引用_Lambda扩展.c3_引用类方法;

public class ConverterDemo {
    public static void main(String[] args) {

        useConverter(s -> Integer.parseInt(s));

        // 引用类方法
        useConverter(Integer::parseInt);

        // lambda 被类方法替代的时候，它的形式参数全部传递给了静态方法

    }

    private static void useConverter(Converter c) {
        int number = c.converter("6666");
        System.out.println(number);
    }
}
