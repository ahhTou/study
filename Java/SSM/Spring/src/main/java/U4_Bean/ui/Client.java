package U4_Bean.ui;

import U4_Bean.impl.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
                "U4_bean.xml"
        );
        IAccountService as1 = (IAccountService) ac.getBean("accountService4");
        as1.saveAccount();
        ac.close();
    }
}
