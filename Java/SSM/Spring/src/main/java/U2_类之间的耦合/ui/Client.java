package U2_类之间的耦合.ui;

import U2_类之间的耦合.dao.IAccountService;
import U2_类之间的耦合.functory.BeanFactory;

public class Client {
    public static void main(String[] args) {
//        IAccountService as = new AccountServiceImpl();
        /**
         * 多例
         */
        IAccountService as = (IAccountService) BeanFactory.getBean("accountService");
        as.saveAccount();
    }
}
