package U14_常用Api.c7_Data;

import java.util.Calendar;

public class c4_Calendar {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();

        System.out.println(c);

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DATE);
        System.out.println(year + "年" + month + "月" + date+ "日");
    }
}
