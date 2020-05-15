package U6_IOC.impl;

import U6_IOC.dao.IAccountDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository("accountDao1")
public class AccountDaoImpl2 implements IAccountDao {
    public void saveAccount() {
        System.out.println("保存了账户");
    }
}
