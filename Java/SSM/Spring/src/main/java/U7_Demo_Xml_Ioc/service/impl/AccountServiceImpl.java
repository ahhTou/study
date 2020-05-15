package U7_Demo_Xml_Ioc.service.impl;

import U7_Demo_Xml_Ioc.dao.IAccountDao;
import U7_Demo_Xml_Ioc.domain.Account;
import U7_Demo_Xml_Ioc.service.IAccountService;

import java.util.List;

/**
 * 账户业务层实现类
 */

public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    @Override
    public Account findAccountById(Integer accountID) {
        return accountDao.findAccountById(accountID);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer accountID) {
        accountDao.deleteAccount(accountID);
    }
}
