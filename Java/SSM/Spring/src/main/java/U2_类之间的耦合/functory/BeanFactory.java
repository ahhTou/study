package U2_类之间的耦合.functory;

import java.io.InputStream;
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

    // 使用静态代码块为Properties 对象赋值
    static {
        // 实例化对象
        props = new Properties();
        InputStream in = BeanFactory.class.getClassLoader()
                .getResourceAsStream("bean.properties");
        try {
            props.load(in);
        } catch (Exception e) {
            // 抛出一个初始化异常
            throw new ExceptionInInitializerError(
                    "初始化properties失败！"
            );
        }
    }

    /**
     * 根据Bean的名称获取bean对象
     *
     * @param beanName
     * @return
     */

    public static Object getBean(String beanName) {
        Object bean = null;
        String beanPath = props.getProperty(beanName);
        try {
            bean = Class.forName(beanPath).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
