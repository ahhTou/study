package U14_AOP_Demo.service;

import U14_AOP_Demo.domain.Account;

import java.util.List;

/**
 * 账户的业务层接口
 */
public interface IAccountService {

    // 查询
    List<Account> findAllAccount();

    // 查询一个
    Account findAccountById(Integer accountID);

    // 保存
    void saveAccount(Account account);

    // 更新
    void updateAccount(Account account);

    // 参数
    void deleteAccount(Integer accountID);

    /**
     *
     * @param sourceName    转出账户名称
     * @param targetName    转入账户名称
     * @param money         转账金额
     */
    void transfer(String sourceName, String targetName, Float money);

}
