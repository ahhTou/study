package U14_常用Api.c7_Data;

import java.util.Date;

public class c2_常用方法 {
    public static void main(String[] args) {
        //创建日期对象
        Date d = new Date();

        //毫秒时间
        System.out.println(d.getTime());
        System.out.println(d.getTime()*1.0/1000/60/60/24/365+"年");

//        long time = 1000*60*60;
        long time = System.currentTimeMillis();
        d.setTime(time);
        System.out.println(d);

    }
}
