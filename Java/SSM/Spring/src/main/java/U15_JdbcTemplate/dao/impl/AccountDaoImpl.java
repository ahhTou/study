package U15_JdbcTemplate.dao.impl;

import U15_JdbcTemplate.dao.IAccountDao;
import U15_JdbcTemplate.domain.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

public class AccountDaoImpl implements IAccountDao {

    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Account findAccountById(Integer accountId) {
        List<Account> a = jdbcTemplate.query(
                "select * from account where id = ?",
                new BeanPropertyRowMapper<Account>(Account.class),
                accountId);
        return a.isEmpty() ? null : a.get(0);
    }

    @Override
    public Account findAccountByName(Integer accountId) {
        List<Account> a = jdbcTemplate.query(
                "select * from account where name = ?",
                new BeanPropertyRowMapper<Account>(Account.class),
                accountId);

        if (a.isEmpty()) return null;
        if (a.size() > 1) throw new RuntimeException("结果集不唯一");
        return a.get(0);
    }

    @Override
    public void updateAccount(Account account) {
        jdbcTemplate.update(
                "update account set `name`=?,money=? where id=?",
                account.getName(),
                account.getMoney(),
                account.getId()
        );
    }


}
