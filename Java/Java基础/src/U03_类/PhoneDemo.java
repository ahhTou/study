package U03_类;

public class PhoneDemo {
    public static void main(String[] args) {
        Phone p = new Phone();
        System.out.println("123" + p);
        System.out.println(p.brand);
        System.out.println(p.price);
        p.brand = "apple";
        p.price = 8699;
        p.call();
        p.sendMessage();
        System.out.println(p.brand);
        System.out.println(p.price);

        Phone p2 = p;
        System.out.println("123" + p);
        System.out.println("123" + p2);

    }
}
