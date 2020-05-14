package U2.ui;

import U2.dao.IAccountService;
import U2.functory.BeanFactory;

public class Client {
    public static void main(String[] args) {
//        IAccountService as = new AccountServiceImpl();
        /**
         * 多例
         */
        IAccountService as = (IAccountService) BeanFactory.getBean("accountService");
        as.saveAccount();

        for (int i = 0; i < 5; i++) {
            IAccountService ss = (IAccountService) BeanFactory.getBean("accountService");
            System.out.println(ss);
            as.saveAccount();
        }
    }
}
