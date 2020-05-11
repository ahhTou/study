package U25_反射.c2_反射.c3_获取成员变量并使用.c1_;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        // 1.获取class字节码对象
        Class<?> c = Class.forName("U25_反射.c2_反射.c3_获取成员变量并使用.c1_.Student");

        // 2. 获取公共成员
        Field[] fields = c.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }

        System.out.println("================");

        // 3. 获取全部成员变量
        Field[] fieldsAll = c.getDeclaredFields();
        for (Field field : fieldsAll) {
            System.out.println(field);
        }


        System.out.println("================");
        // 4. 通过成员变量的名字获取成员变量
        Field address = c.getField("address");
        Constructor<?> constructor = c.getConstructor();
        Object obj = constructor.newInstance();
        address.set(obj, "西安");
        System.out.println(obj);


//        Student s = new Student();
//        s.address = "西安";
//        System.out.println(s);


    }
}
