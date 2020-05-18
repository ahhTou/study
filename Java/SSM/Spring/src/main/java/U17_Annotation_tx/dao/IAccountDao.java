package U17_Annotation_tx.dao;

import U17_Annotation_tx.domain.Account;

public interface IAccountDao {

    Account findAccountById(Integer accountId);

    Account findAccountByName(String accountId);

    void updateAccount(Account account);


}
