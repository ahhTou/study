package U25_反射.c2_反射.c4_获取成员方法并使用.c1_;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        // 1.获取class字节码对象
        Class<?> c = Class.forName("U25_反射.c2_反射.c4_获取成员方法并使用.c1_.Student");

        // 2. 获取全部公共方法 包括 继承的方法
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        System.out.println("==========");

        // 3. 获取本类 的私有和公共方法
        Method[] declaredMethods = c.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println(method);
        }

        // 4. 得到指定的方法 参数是函数的名字 和 方法参数
        Method m = c.getMethod("method1");
        Constructor<?> constructor = c.getConstructor();
        Object obj = constructor.newInstance();

        // method.invoke(obj,...args)
        m.invoke(obj);


    }
}
