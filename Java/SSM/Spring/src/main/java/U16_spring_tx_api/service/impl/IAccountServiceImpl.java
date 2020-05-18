package U16_spring_tx_api.service.impl;

import U16_spring_tx_api.dao.IAccountDao;
import U16_spring_tx_api.domain.Account;
import U16_spring_tx_api.service.IAccountService;

public class IAccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account findAccountById(Integer accountId) {
        return null;
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {
            System.out.println("Transfer……");
            // 1.根据名称查询转出账户
            Account source = accountDao.findAccountByName(sourceName);
            // 2.根据名称查询转入账户
            Account target = accountDao.findAccountByName(targetName);
            // 3.转出账户捡钱
            source.setMoney(source.getMoney() - money);
            // 4.转入转账加钱
            target.setMoney(target.getMoney() + money);
            // 5.更新转出账号
            accountDao.updateAccount(source);

            int i = 1/0;

            // 6.更新转入账户
            accountDao.updateAccount(target);
            System.out.println("Transfer Is Ok");
    }
}
