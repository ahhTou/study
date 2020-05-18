package U15_JdbcTemplate.dao;

import U15_JdbcTemplate.domain.Account;

public interface IAccountDao {

    Account findAccountById(Integer accountId);

    Account findAccountByName(Integer accountId);

    void updateAccount(Account account);


}
