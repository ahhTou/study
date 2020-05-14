package U3_KonwSpring.impl;

import U3_KonwSpring.dao.IAccountDao;
import U3_KonwSpring.dao.IAccountService;

public class AccountServiceImpl implements IAccountService {
    private IAccountDao accountDao = new AccountDaoImpl();

    public AccountServiceImpl(){
        System.out.println("对象创建了");
    }

    public void saveAccount() {
        accountDao.saveAccount();
    }
}
