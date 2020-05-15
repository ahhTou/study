package U7_Demo_Xml_Ioc.dao;

import U7_Demo_Xml_Ioc.domain.Account;

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


}
