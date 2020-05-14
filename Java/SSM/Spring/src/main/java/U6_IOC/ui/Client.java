package U6_IOC.ui;

import U4_Bean.impl.IAccountService;
import U6_IOC.dao.IAccountDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
                "U6_bean.xml"
        );
        IAccountService as1 = (IAccountService) ac.getBean("accountService");
        as1.saveAccount();

        IAccountDao adao = (IAccountDao) ac.getBean("accountDao");
        System.out.println(adao);
    }
}
