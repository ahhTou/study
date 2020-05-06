package U09_修饰符;

public class Demo {
    public static void main(String[] args) {
        Zi z = new Zi();
        z.method();
        z.show();

        // final 修饰引用内容，不能变地址，可以变内容
        final Zi z2 = new Zi();
        z2.name = "ahhTouPro";
        z2.show();



    }
}
