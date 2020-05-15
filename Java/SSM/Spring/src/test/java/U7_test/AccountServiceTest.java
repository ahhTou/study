package U7_test;

import U7_Demo_Xml_Ioc.domain.Account;
import U7_Demo_Xml_Ioc.service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class AccountServiceTest {
    // 1.获取容器
    ApplicationContext ac = new
            ClassPathXmlApplicationContext("U7_bean.xml");
    // 2.得到业务层对象
    IAccountService as = ac.getBean(
            "accountService",
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
