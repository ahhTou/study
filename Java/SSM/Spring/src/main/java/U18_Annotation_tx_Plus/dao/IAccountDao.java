package U18_Annotation_tx_Plus.dao;

import U18_Annotation_tx_Plus.domain.Account;

public interface IAccountDao {

    Account findAccountById(Integer accountId);

    Account findAccountByName(String accountId);

    void updateAccount(Account account);


}
