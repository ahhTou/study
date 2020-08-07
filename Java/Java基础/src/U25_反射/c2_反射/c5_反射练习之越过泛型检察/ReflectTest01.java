package U25_反射.c2_反射.c5_反射练习之越过泛型检察;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ReflectTest01 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 将 String 类型添加 到 ArrayList<Integer> 中

        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(10);
        arrayList.add(20);

        Class<? extends ArrayList> c = arrayList.getClass();
        Method add = c.getMethod("add", Object.class);
        add.invoke(arrayList, "hello");

        System.out.println(arrayList);

    }
}
