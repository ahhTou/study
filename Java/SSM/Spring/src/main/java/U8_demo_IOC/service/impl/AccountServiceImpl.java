package U8_demo_IOC.service.impl;

import U8_demo_IOC.dao.IAccountDao;
import U8_demo_IOC.domain.Account;
import U8_demo_IOC.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 账户业务层实现类
 */
@Service("U8accountService")
public class AccountServiceImpl implements IAccountService {

//    @Autowired

    @Resource(name = "U8accountDao")
    private IAccountDao accountDao;

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
