package U9_test;

import U9_to_solve_U8_xml.SpringConfiguration;
import U9_to_solve_U8_xml.domain.Account;
import U9_to_solve_U8_xml.service.IAccountService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

/**
 * 使用Junit单元测试，测试我们的配置
 * Spring整合junit的配置
 */
public class AccountServiceTest {
    // 1.获取容器
//    ApplicationContext ac = new
//            ClassPathXmlApplicationContext("U9_bean.xml");
    //可以传入多个config类文件
    ApplicationContext ac = new
            AnnotationConfigApplicationContext(SpringConfiguration.class);
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
