package U25_反射.c2_反射.c2_获取构造方法并使用.c1_;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        // 1.获取class字节码对象
        Class<?> c = Class.forName("U25_反射.c2_反射.c2_获取构造方法并使用.c1_.Student");

        // 2.得到 公共 的构造方法
        Constructor<?>[] cons = c.getConstructors();
        for (Constructor con : cons) {
            System.out.println(con);
        }

        System.out.println("==========");

        // 3.得到 所有 构造方法
        Constructor<?>[] cons2 = c.getDeclaredConstructors();
        for (Constructor con : cons2) {
            System.out.println(con);
        }

        System.out.println("==============");

        // 4. 得到一个 构造函数 需要参数：参数对象，和数据类型对应的字节码对象
        Constructor<?> con = c.getConstructor(); // 得到无参构造
        Object obj = con.newInstance();
        System.out.println(obj);

//        c.getDeclaredConstructor();
    }
}
