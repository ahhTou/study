package U17_Annotation_tx.service.impl;

import U17_Annotation_tx.dao.IAccountDao;
import U17_Annotation_tx.domain.Account;
import U17_Annotation_tx.service.IAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("accountService")
@Transactional
public class IAccountServiceImpl implements IAccountService {

    @Resource(name = "accountDao")
    private IAccountDao accountDao;

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
            // 6.更新转入账户
            accountDao.updateAccount(target);
            System.out.println("Transfer Is Ok");
    }
}
