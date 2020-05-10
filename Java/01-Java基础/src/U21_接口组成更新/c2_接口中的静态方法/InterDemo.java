package U21_接口组成更新.c2_接口中的静态方法;

public class InterDemo {
    public static void main(String[] args) {
        Inter i = new InterImpl();
        i.show();
        i.method();
        Inter.test();
    }
}
