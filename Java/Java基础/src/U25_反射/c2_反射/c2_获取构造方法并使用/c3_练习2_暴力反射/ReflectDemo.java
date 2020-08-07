package U25_反射.c2_反射.c2_获取构造方法并使用.c3_练习2_暴力反射;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        // 1.获取class字节码对象
        Class<?> c = Class.forName("U25_反射.c2_反射.c2_获取构造方法并使用.c3_练习2_暴力反射.Student");

        Constructor<?> declaredConstructor = c.getDeclaredConstructor(String.class);

        // 暴力反射 不能通过私有构造方法
        declaredConstructor.setAccessible(true); // 取消访问检察

        Object ahhTou = declaredConstructor.newInstance("ahhTou");

        System.out.println(ahhTou);


    }
}
