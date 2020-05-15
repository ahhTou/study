package U8_test;

import U8_demo_IOC.domain.Account;
import U8_demo_IOC.service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class AccountServiceTest {
    // 1.获取容器
    ApplicationContext ac = new
            ClassPathXmlApplicationContext("U8_bean.xml");
    // 2.得到业务层对象
    IAccountService as = ac.getBean(
            "U8accountService",
            IAccountService.class
    );

    @Test
    public void testFinaAll() {
        // 3.执行方法
        List<Account> accounts = as.findAllAccount();
        accounts.forEach(System.out::println);

    }

    @Test
    public void testFindOne() {
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        Account account = new Account();
        account.setName("ahhTou");
        account.setMoney(100f);
        as.saveAccount(account);
    }

    @Test
    public void testUpdate() {
        Account account = as.findAccountById(9);
        account.setMoney(666f);
        as.updateAccount(account);


    }

    @Test
    public void testDelete() {
        as.deleteAccount(9);
    }

}
