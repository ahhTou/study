package U2.functory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 一个创建bean对象的工厂
 * <p>
 * Bean 在计算机中可重用组件的含义
 * JavaBean  用java语言编写的可重用组件
 * JavaBean > 实体类
 * 它就是创建文明的service 和 dao 对象的
 * <p>
 * 1.需要一个配置文件，配置我们的service 和 dao
 * 配置内容：唯一标识 = 全限定类名（key = value）
 * 2.通过读取配置文件中的配置内容，反射创建内容
 * <p>
 * 配置文件我们可以是 properties（足够简单） 和 xml
 */

public class BeanFactory {
    // 定义一个properties 对象
    private static Properties props;

    // 定义一个Map 用于存放我们创建的对象，我们称之为容器
    public static Map<String, Object> beans;

    // 使用静态代码块为Properties 对象赋值
    static {
        try {
            // 实例化对象
            props = new Properties();
            // 获取properties文件流对象
            InputStream in = BeanFactory.class.getClassLoader()
                    .getResourceAsStream("bean.properties");
            props.load(in);
            // 实例化容器
            beans = new HashMap<String, Object>();
            // 取出配置文件中的所有key
            Enumeration<Object> keys = props.keys();
            // 遍历取出每个key
            while (keys.hasMoreElements()) {
                // 取出每个key
                String key = keys.nextElement().toString();
                // 根据key获取value
                String beanPath = props.getProperty(key);
                System.out.println(beanPath);
                // 反射创建对象
                System.out.println("123");
                Object value = Class.forName(beanPath).newInstance();
                // 把key和value存入容器中
                beans.put(key, value);
            }
        } catch (Exception e) {
            // 抛出一个初始化异常
            e.printStackTrace();
            throw new ExceptionInInitializerError(
                    "初始化properties失败！"
            );
        }
    }

    /**
     * 根据bean的名称获取对象
     *
     * @param beanName
     * @return
     */

    public static Object getBean(String beanName) {
        System.out.println("123");
        return beans.get(beanName);
    }

    /**
     * 根据Bean的名称获取bean对象
     *
     * @param beanName
     * @return public static Object getBean(String beanName) {
    Object bean = null;
    String beanPath = props.getProperty(beanName);
    try {
    bean = Class.forName(beanPath).newInstance();
    // 每次都会重新初始化
    } catch (Exception e) {
    e.printStackTrace();
    }
    return bean;
    }
     */
}
