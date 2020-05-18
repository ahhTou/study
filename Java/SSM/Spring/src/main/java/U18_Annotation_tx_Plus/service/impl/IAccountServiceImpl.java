package U18_Annotation_tx_Plus.service.impl;

import U18_Annotation_tx_Plus.dao.IAccountDao;
import U18_Annotation_tx_Plus.domain.Account;
import U18_Annotation_tx_Plus.service.IAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("accountService")
//@Transactional
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class IAccountServiceImpl implements IAccountService {

    @Resource(name = "accountDao")
    private IAccountDao accountDao;

    @Override
    public Account findAccountById(Integer accountId) {
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
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

            int i = 1/0;

            accountDao.updateAccount(target);
            System.out.println("Transfer Is Ok");
    }
}
