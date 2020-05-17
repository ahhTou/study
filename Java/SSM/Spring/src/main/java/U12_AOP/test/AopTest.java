package U12_AOP.test;


import U12_AOP.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试AOP的配置
 */
public class AopTest {
    public static void main(String[] args) {
        // 1.获取容器
        ApplicationContext ac = new
                ClassPathXmlApplicationContext("U12_Bean.xml");

        // 2.获取对象
        IAccountService as = ac.getBean(
                "accountService",
                IAccountService.class
        );

        // 3.执行方法
        as.saveAccount();
    }
}
