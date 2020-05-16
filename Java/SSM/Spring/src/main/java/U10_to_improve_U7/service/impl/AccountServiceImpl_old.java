package U10_to_improve_U7.service.impl;

import U10_to_improve_U7.dao.IAccountDao;
import U10_to_improve_U7.domain.Account;
import U10_to_improve_U7.service.IAccountService;
import U10_to_improve_U7.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账户业务层实现类
 * <p>
 * 事务控制应该都在业务层
 */

public class AccountServiceImpl_old implements IAccountService {

    private IAccountDao accountDao;
    private TransactionManager txManager;

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public List<Account> findAllAccount() {
        try {
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            List<Account> accounts = accountDao.findAllAccount();
            //3.提交事务
            txManager.commit();
            //4.返回结果
            return accounts;
        } catch (Exception e) {
            //5.回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            //6.释放操作
            txManager.release();
        }
    }

    @Override
    public Account findAccountById(Integer accountID) {
        try {
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            Account account = accountDao.findAccountById(accountID);
            //3.提交事务
            txManager.commit();
            //4.返回结果
            return account;
        } catch (Exception e) {
            //5.回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            //6.释放操作
            txManager.release();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            accountDao.updateAccount(account);
            //3.提交事务
            txManager.commit();
            //4.返回结果
        } catch (Exception e) {
            //5.回滚操作
            txManager.rollback();
        } finally {
            //6.释放操作
            txManager.release();
        }
    }

    @Override
    public void deleteAccount(Integer accountID) {
        try {
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            accountDao.deleteAccount(accountID);
            //3.提交事务
            txManager.commit();
            //4.返回结果
        } catch (Exception e) {
            //5.回滚操作
            txManager.rollback();
        } finally {
            //6.释放操作
            txManager.release();
        }

    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        try {
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            {
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
            }
            //3.提交事务
            txManager.commit();
            //4.返回结果
        } catch (Exception e) {
            //5.回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            //6.释放操作
            txManager.release();
        }

    }

}
