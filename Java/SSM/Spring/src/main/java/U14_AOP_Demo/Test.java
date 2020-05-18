package U14_AOP_Demo;

import U14_AOP_Demo.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(
                SpringConfiguration.class
        );

        IAccountService as = (IAccountService) ac.getBean("accountService");

        as.transfer("aaa","bbb",100f);
    }

}
