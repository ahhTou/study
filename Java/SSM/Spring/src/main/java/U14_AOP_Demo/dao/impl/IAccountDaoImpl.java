package U14_AOP_Demo.dao.impl;

import U14_AOP_Demo.dao.IAccountDao;
import U14_AOP_Demo.domain.Account;
import U14_AOP_Demo.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "accountDao")
public class IAccountDaoImpl implements IAccountDao {

    @Autowired
    private QueryRunner runner;

    @Autowired
    private ConnectionUtils connectionUtils;

    @Override
    public List<Account> findAllAccount() {
        try {
            return runner.query(
                    connectionUtils.getThreadConnection(),
                    "select * from account",
                    new BeanListHandler<Account>(Account.class)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findAccountById(Integer accountID) {
        try {
            return runner.query(
                    connectionUtils.getThreadConnection(),
                    "select * from account where id = ?",
                    new BeanHandler<Account>(Account.class),
                    accountID
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            runner.update(
                    connectionUtils.getThreadConnection(),
                    "insert into account(name,money) value(?, ?)",
                    account.getName(),
                    account.getMoney()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            runner.update(
                    connectionUtils.getThreadConnection(),
                    "update account set `name` = ?,money = ? where id = ?",
                    account.getName(),
                    account.getMoney(),
                    account.getId()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteAccount(Integer accountID) {
        try {
            runner.update(
                    connectionUtils.getThreadConnection(),
                    "delete from account where id =?",
                    accountID
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findAccountByName(String accountName) {
        try {
            List<Account> accounts = runner.query(
                    connectionUtils.getThreadConnection(),
                    "select * from account where `name`= ?",
                    new BeanListHandler<Account>(Account.class),
                    accountName
            );
            if (accounts == null || accounts.size() == 0) {
                return null;
            } else if (accounts.size() > 1) {
                throw new RuntimeException("结果集不唯一，数据有问题");
            } else return accounts.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
