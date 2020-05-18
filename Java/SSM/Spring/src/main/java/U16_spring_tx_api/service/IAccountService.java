package U16_spring_tx_api.service;

import U16_spring_tx_api.domain.Account;

public interface IAccountService {

    Account findAccountById(Integer accountId);

    void transfer(String sourceName,String target, Float money);

}
