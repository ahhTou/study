package U2.impl;

import U2.dao.IAccountService;
import U2.dao.IAccountDao;
import U2.functory.BeanFactory;

public class AccountServiceImpl implements IAccountService {
    private IAccountDao accountDao = (IAccountDao) BeanFactory
            .getBean("accountDao");

    public void saveAccount() {
        accountDao.saveAccount();
    }
}
