package U12_AOP.service.impl;

import U12_AOP.service.IAccountService;

/**
 * 账户的业务层实现类
 */
public class IAccountServiceImpl implements IAccountService {

    @Override
    public void saveAccount() {
        System.out.println("执行了保存");
    }

    @Override
    public void updateAccount(int i) {
        System.out.println("执行了更新");
    }

    @Override
    public int deleteAccount() {
        System.out.println("执行了删除");
        return 0;
    }
}
