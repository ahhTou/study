package U3_KonwSpring.ui;

import U3_KonwSpring.dao.IAccountDao;
import U3_KonwSpring.dao.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Client {
    /**
     * 获取spring的IOC核心容器 并根据id获取对象
     * ApplicationContext 的 三个 常用实现类
     *  ClassPathXmlApplicationContext 可以加载类路径下的配置文件，要求配置文件必须在类路径下
     *  FileSystemXmlApplicationContext 可以加载磁盘任意路径下的配置文件（必须有访问权限）
     *
     *  AnnotationConfigApplicationContext 他是读取渡注解创建文件，以后的内容
     *
     * 核心容器的两个接口引发出的问题
     * ApplicationContext【单例对象适用】 采用此接口
     *      它在创建核心容器是，创建对象采取的策略是采用立即加载的方式
     *      也就是说已读取完毕，马上就创建配置文件中配置的对象
     * BeanFactory【多例对象适用】
     *      它在创建核心容器是，创建对象采取的策略是采用延迟加载的方式
     *      也就是说什么时候读取对象了，什么时候才真正的创建对象
     *
     * @param args
     */
    public static void main(String[] args) {

        //1.获取核心容器对象

            // 1.ClassPathXmlApplicationContext 可以加载类路径下的配置文件，要求配置文件必须在类路径下
        ApplicationContext ac = new ClassPathXmlApplicationContext(
                "bean.xml"
        );

            // 2.FileSystemXmlApplicationContext 可以加载磁盘任意路径下的配置文件（必须有访问权限）
//        ApplicationContext ac = new FileSystemXmlApplicationContext(
//                "D:\\PrivateFiles\\_study\\learn\\study\\Java\\SSM\\Spring\\src\\main\\resources\\U5_Bean.xml"
//        );

        //2.获取id获取bean对象
            //1.通过强转类型
        IAccountService as = (IAccountService) ac.getBean("accountService");
            //2.通过传递对象字节码
        IAccountDao adao = ac.getBean("accountDao", IAccountDao.class);

        System.out.println(as);
        System.out.println(adao);

    }
}
