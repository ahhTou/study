package U14_常用Api.c5_Integer包装类;

public class c3_Int和String转换 {
    public static void main(String[] args) {
        int number = 100;

        // 方式 1
        String s1 = "" + number;
        System.out.println(s1);

        // 方式 2
        String s2 = String.valueOf(number);
        System.out.println(s2);
        System.out.println("=====");

        // String to int
        String s = "100";

        // 方式 1
        Integer i = Integer.valueOf(s);
        int x = i.intValue();
        System.out.println(x);

        //方式 2

        int y = Integer.parseInt(s);
        System.out.println(y);


    }
}
