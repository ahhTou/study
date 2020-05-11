package U25_反射.c2_反射.c2_获取构造方法并使用.c2_练习_通过参数得到构造函数;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        // 1.获取class字节码对象
        Class<?> c = Class.forName("U25_反射.c2_反射.c2_获取构造方法并使用.c2_练习_通过参数得到构造函数.Student");

        Constructor<?> constructor = c.getConstructor(String.class, int.class, String.class);

        Object obj = constructor.newInstance("ahhTou", 30, "西安");

        System.out.println(obj);
    }
}
