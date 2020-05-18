package U13_Annotation_AOP.test;


import U13_Annotation_AOP.SpringConfiguration;
import U13_Annotation_AOP.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试AOP的配置
 */
public class AopTest {
    public static void main(String[] args) {
//        // 1.获取容器
//        ApplicationContext ac = new
//                ClassPathXmlApplicationContext("U13_Bean.xml");
//
//        // 2.获取对象
//        IAccountService as = (IAccountService) ac.getBean("accountService");
//
//        // 3.执行方法
//        as.saveAccount();

        //


        // 1.获取容器
        ApplicationContext ac2 = new
                AnnotationConfigApplicationContext(SpringConfiguration.class);

        IAccountService as2 = (IAccountService) ac2.getBean("accountService");

        as2.saveAccount();
    }
}
