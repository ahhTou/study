package U15_JdbcTemplate.JdbcTemplate;

import U15_JdbcTemplate.dao.IAccountDao;
import U15_JdbcTemplate.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcTemplateDemo4 {
    public static void main(String[] args) {

        ApplicationContext ac = new ClassPathXmlApplicationContext("U15_bean.xml");

        IAccountDao accountDao = ac.getBean("accountDao", IAccountDao.class);

        Account account =  accountDao.findAccountById(1);

        System.out.println(account);

        account.setMoney(100000f);

        accountDao.updateAccount(account);




    }
}
