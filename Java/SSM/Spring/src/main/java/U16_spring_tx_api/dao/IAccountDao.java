package U16_spring_tx_api.dao;

import U16_spring_tx_api.domain.Account;

public interface IAccountDao {

    Account findAccountById(Integer accountId);

    Account findAccountByName(String accountId);

    void updateAccount(Account account);


}
