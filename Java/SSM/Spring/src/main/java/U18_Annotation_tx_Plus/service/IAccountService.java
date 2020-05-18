package U18_Annotation_tx_Plus.service;

import U18_Annotation_tx_Plus.domain.Account;

public interface IAccountService {

    Account findAccountById(Integer accountId);

    void transfer(String sourceName,String target, Float money);

}
