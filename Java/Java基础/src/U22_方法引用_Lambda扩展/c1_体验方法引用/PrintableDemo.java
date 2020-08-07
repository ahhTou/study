package U22_方法引用_Lambda扩展.c1_体验方法引用;

public class PrintableDemo {
    public static void main(String[] args) {

        usePrintable(s -> System.out.println(s));

        // 方法引用符 ::
        usePrintable(System.out::println);

        // 可推导的就是可省略的
    }

    private static void usePrintable(Printable p) {
        p.printString("爱生活爱java");
    }
}
