package U07_类的继承.c5MethodsRewrite;

public class PhoneDemo {
    public static void main(String[] args) {
        Phone p = new Phone();
        p.call("ahhTou");
        System.out.println("==================");

        NewPhone np = new NewPhone();
        np.call("ahhTou");
        // 私有无法继承
//        np.show();
    }
}
