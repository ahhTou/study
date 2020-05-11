package U25_反射.c2_反射.c4_获取成员方法并使用.c2_练习;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        // 1.获取class字节码对象
        Class<?> c = Class.forName("U25_反射.c2_反射.c4_获取成员方法并使用.c2_练习.Student");

        Constructor<?> constructor = c.getConstructor();
        Object obj = constructor.newInstance();

        Method m1 = c.getMethod("method1");
        m1.invoke(obj);

        Method m2 = c.getMethod("method2", String.class);
        m2.invoke(obj, "ahhTou");

        Method m3 = c.getMethod("method3", String.class, int.class);
        Object o = m3.invoke(obj, "ahhTou", 30);
        System.out.println(o);

        // 暴力访问
        Method m4 = c.getDeclaredMethod("function");
        m4.setAccessible(true);
        m4.invoke(obj);

    }
}
