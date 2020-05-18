package U16_spring_tx_api.dao.impl;

import U15_JdbcTemplate.dao.impl.JdbcDaoSupport;
import U16_spring_tx_api.dao.IAccountDao;
import U16_spring_tx_api.domain.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

public class AccountDaoImpl extends JdbcDaoSupport implements IAccountDao {


    @Override
    public Account findAccountById(Integer accountId) {
        List<Account> a = getJdbcTemplate().query(
                "select * from account where id = ?",
                new BeanPropertyRowMapper<Account>(Account.class),
                accountId);
        return a.isEmpty() ? null : a.get(0);
    }

    @Override
    public Account findAccountByName(String accountId) {
        List<Account> a = getJdbcTemplate().query(
                "select * from account where name = ?",
                new BeanPropertyRowMapper<Account>(Account.class),
                accountId);
        if (a.isEmpty()) return null;
        if (a.size() > 1) throw new RuntimeException("结果集不唯一");
        return a.get(0);
    }

    @Override
    public void updateAccount(Account account) {
        getJdbcTemplate().update(
                "update account set `name`=?,money=? where id=?",
                account.getName(),
                account.getMoney(),
                account.getId()
        );
    }
}
