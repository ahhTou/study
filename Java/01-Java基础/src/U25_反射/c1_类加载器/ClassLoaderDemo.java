package U25_反射.c1_类加载器;

public class ClassLoaderDemo {
    public static void main(String[] args) {
        ClassLoader c = ClassLoader.getSystemClassLoader();
        System.out.println(c);

        System.out.println(c.getParent());

        System.out.println(c.getParent().getParent());
    }
}
