package U14_常用Api.c7_Data;

import java.util.Date;

public class c1_构造 {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);

        // 改变时区
        long data = 1000*60*60;
        Date d2 = new Date(data);
        System.out.println(d2);
    }
}
