package U10_test;


import U10_to_improve_U7.domain.Account;
import U10_to_improve_U7.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:U10_bean.xml")

public class AccountServiceTest {
    @Autowired
    @Qualifier("proxyAccountService")
    private final IAccountService as = null;
    @Test
    public void testSave() {
        Account account = new Account();
        account.setName("ahhTou");
        account.setMoney(100f);
        as.saveAccount(account);
    }
    @Test
    public void testFindAll() {
        // 3.执行方法
        as.findAllAccount();
//        List<Account> accounts = as.findAllAccount();
//        accounts.forEach(System.out::println);

    }

    @Test
    public void testFindOne(){
        as.findAccountById(1);
    }

    @Test
    public void testTransfer() {
        as.transfer("aaa", "bbb", 100f);
    }

}
