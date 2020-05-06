package U12_接口.c2特点;

public class InterfaceDemo {

    public static void main(String[] args) {
        Inter i = new InterImpl();
        System.out.println(i.num);
//        i.num2 = 30;
        System.out.println(i.num2);
        System.out.println(Inter.num);

    }
}
