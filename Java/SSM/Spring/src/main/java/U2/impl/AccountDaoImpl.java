package U2.impl;

import U2.dao.IAccountDao;

public class AccountDaoImpl implements IAccountDao {
    public void saveAccount(){
        System.out.println("保存了账户");
    }
}
