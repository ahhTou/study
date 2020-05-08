package U15_异常;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class c2_throws {
    public static void main(String[] args) {
        System.out.println("开始");
        try {
            // 仅抛出异常依然无法输出
            method2();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        method();
        System.out.println("结束");
    }

    // 运行时异常 抛出但是不会继续进行
    public static void method() throws ArrayIndexOutOfBoundsException {
        int[] arr = {1, 2, 3};
        // 运行时异常
        System.out.println(arr[3]);
    }

    public static void method2() throws ParseException {

        String s = "2048-08-09";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date d = sdf.parse(s);            // 编译时异常
        System.out.println(d);

    }
}
