package U15_异常;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class c1_try_catch {
    public static void main(String[] args) {
        System.out.println("开始");
        method();
        method2();
        System.out.println("结束");
    }

    public static void method() {
        try {
            int[] arr = {1, 2, 3};
            // 运行时异常
            System.out.println(arr[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("错误信息：" + e.toString());
            System.out.println("详细错误字符串：" + e.getMessage());
            e.printStackTrace();  // 把异常信息输出到控制台
            System.out.println("你访问的索引不存在");
        }
    }

    public static void method2() {
        try {
            String s = "2048-08-09";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date d = sdf.parse(s);            // 编译时异常
            System.out.println(d);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
