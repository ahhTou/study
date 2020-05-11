package U25_反射.c2_反射.c6_反射练习运行配置文件指定内容;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class ReflectTest02 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Properties prop = new Properties();
        FileReader fr = new FileReader("./src/U25_反射/c2_反射/c6_反射练习运行配置文件指定内容/class.txt");

        prop.load(fr);
        fr.close();

        String className = prop.getProperty("className");
        String methodName = prop.getProperty("methodName");

        Class<?> c = Class.forName(className);
        Object obj = c.newInstance();

        Method m = c.getMethod(methodName);
        m.invoke(obj);


    }
}
