package U5_DI.ui;

import U4_Bean.impl.IAccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
                "U5_bean.xml"
        );
        IAccountService as1 = (IAccountService) ac.getBean("accountService3");
        as1.saveAccount();
    }
}
