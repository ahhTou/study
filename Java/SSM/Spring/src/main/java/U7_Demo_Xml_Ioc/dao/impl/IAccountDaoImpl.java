package U7_Demo_Xml_Ioc.dao.impl;

import U7_Demo_Xml_Ioc.dao.IAccountDao;
import U7_Demo_Xml_Ioc.domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class IAccountDaoImpl implements IAccountDao {

    private QueryRunner runner;

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    @Override
    public List<Account> findAllAccount() {
        try {
            return runner.query(
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
                    "delete from account where id =?",
                    accountID
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
