package U14_AOP_Demo.service.impl;

import U14_AOP_Demo.dao.IAccountDao;
import U14_AOP_Demo.domain.Account;
import U14_AOP_Demo.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 账户业务层实现类
 * <p>
 * 事务控制应该都在业务层
 */

@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    @Autowired
    @Qualifier("accountDao")
    private IAccountDao accountDao;


    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public List<Account> findAllAccount() {
        System.out.println("findAllAccount");
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

    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        System.out.println("Transfer……");
        // 1.根据名称查询转出账户
        Account source = accountDao.findAccountByName(sourceName);
        // 2.根据名称查询转入账户
        Account target = accountDao.findAccountByName(targetName);
        // 3.转出账户捡钱
        source.setMoney(source.getMoney() - money);
        // 4.转入转账加钱
        target.setMoney(target.getMoney() + money);
        // 5.更新转出账号
        accountDao.updateAccount(source);
        // 6.更新转入账户
        accountDao.updateAccount(target);
        System.out.println("Transfer Is Ok");


    }

}
