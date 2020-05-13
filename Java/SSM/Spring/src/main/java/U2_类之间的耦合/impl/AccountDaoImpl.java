package U2_类之间的耦合.impl;

import U2_类之间的耦合.dao.IAccountDao;

public class AccountDaoImpl implements IAccountDao {
    public void saveAccount(){
        System.out.println("保存了账户");
    }
}
