package U17_Annotation_tx.service;

import U17_Annotation_tx.domain.Account;

public interface IAccountService {

    Account findAccountById(Integer accountId);

    void transfer(String sourceName,String target, Float money);

}
