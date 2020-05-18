package U18_Annotation_tx_Plus.dao.impl;

import U18_Annotation_tx_Plus.dao.IAccountDao;
import U18_Annotation_tx_Plus.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository("accountDao")
public class AccountDaoImpl  implements IAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Account findAccountById(Integer accountId) {
        List<Account> a = jdbcTemplate.query(
                "select * from account where id = ?",
                new BeanPropertyRowMapper<Account>(Account.class),
                accountId);
        return a.isEmpty() ? null : a.get(0);
    }

    @Override
    public Account findAccountByName(String accountId) {
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
