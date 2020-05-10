package U22_方法引用_Lambda扩展.c4_引用对象的实例方法;

public class PrinterDemo {
    public static void main(String[] args) {


        usePrinter(s -> System.out.println(s.toUpperCase()));

        // 引用对象的实例方法
        PrintString ps = new PrintString();
        usePrinter(ps::printUpper);
        // 静态时
//        usePrinter(PrintString::printUpper);

        // lambda 表达式被对象的实例化方法替代的时候，
        // 他的形式参数全部传递给该方法作为参数

    }

    private static void usePrinter(Printer p) {
        p.printUpperCase("helloWorld");
    }
}
