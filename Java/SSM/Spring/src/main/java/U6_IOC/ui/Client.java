package U6_IOC.ui;
import U6_IOC.dao.IAccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
                "U6_bean.xml"
        );
        IAccountService as1 = (IAccountService) ac.getBean("accountService");
        IAccountService as2 = (IAccountService) ac.getBean("accountService");
//        as.saveAccount();
        System.out.println(as1 == as2);
        ac.close();
    }
}
