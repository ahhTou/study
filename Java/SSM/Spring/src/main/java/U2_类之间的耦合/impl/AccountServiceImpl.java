package U2_类之间的耦合.impl;

import U2_类之间的耦合.dao.IAccountService;
import U2_类之间的耦合.dao.IAccountDao;
import U2_类之间的耦合.functory.BeanFactory;

public class AccountServiceImpl implements IAccountService {
    private IAccountDao accountDao = (IAccountDao) BeanFactory
            .getBean("accountDao");

    public void saveAccount() {
        accountDao.saveAccount();
    }
}
