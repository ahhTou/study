package U10_to_improve_U7.dao;

import U10_to_improve_U7.domain.Account;

import java.util.List;

public interface IAccountDao {

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
     * 根据名称查询账户
     * @param account
     * @return 有唯一结果就返回，没有结果返回null
     *         结果超出一个就抛异常
     */
    Account findAccountByName(String account);


}
