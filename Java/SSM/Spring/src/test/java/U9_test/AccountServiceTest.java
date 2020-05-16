package U9_test;

import U9_to_solve_U8_xml.SpringConfiguration;
import U9_to_solve_U8_xml.domain.Account;
import U9_to_solve_U8_xml.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 使用Junit单元测试，测试我们的配置
 * Spring整合junit的配置
 * 1.导入spring整合junit的jar包（坐标）
 * 2.使用Junit提供的一个注解把原有的注解，替换成Spring提供的
 *
 * @Runwith 3.告知spring运行期，spring和ioc创建是基于xml还是注解，并说明位置
 * @ContextConfiguration locations：指定xml文件的位置，加上classpath关键字，标识在该类下
 * classes：指定注解类所在的位置
 * 当我们使用spring 5.x版本的时候，要求junit的jar包必须是4.12及以上
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class AccountServiceTest {
//     1.获取容器
//    ApplicationContext ac = new
//            ClassPathXmlApplicationContext("U9_bean.xml");
//    可以传入多个config类文件
//    ApplicationContext ac = new
//            AnnotationConfigApplicationContext(SpringConfiguration.class);
//    // 2.得到业务层对象
//    IAccountService as = ac.getBean(
//            "accountService",
//            IAccountService.class
//    );

    @Autowired
    private final IAccountService as = null;

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
