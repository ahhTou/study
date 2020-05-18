package U15_JdbcTemplate.JdbcTemplate;

import U15_JdbcTemplate.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcTemplateDemo3 {
    public static void main(String[] args) {

        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("U15_bean.xml");

        // 2.获取对象
        JdbcTemplate jt = ac.getBean("jdbcTemplate", JdbcTemplate.class);

        // 3.执行操作
        //保存
//        jt.update(
//                "insert into account(name, money) values(?, ?)",
//                "eee","3333f"
//                );
        //更新
//        jt.update(
//                "update account set name=?, money=? where id = ?",
//                "test","4567","24"
//                );
        //删除
//        jt.update("delete from account where id=?",24);
        //查询所有
        System.out.println(" ");
        System.out.println("↓ 查询全部 ↓");
        System.out.println(" ");

//        List<Account> accountList = jt.query("select * from account where money > ?",new AccountRowMapper(),1f);
        List<Account> accountList = jt.query("select * from account where money > ?",new BeanPropertyRowMapper<Account>(Account.class),1f);
        for(Account account : accountList){
            System.out.println(account);
        }

        System.out.println(" ");
        System.out.println("↓ 查询一个 ↓");
        System.out.println(" ");

        //查询一个
        List<Account> account = jt.query("select * from account where id = ?",new BeanPropertyRowMapper<Account>(Account.class),1);
        System.out.println(account.isEmpty()?"没有内容" : account.get(0));

        System.out.println(" ");
        System.out.println("↓ 查询一行一列 ↓");
        System.out.println(" ");


        //查询返回一行一列
        Integer count = jt.queryForObject("select count(*) from account ",Integer.class);
        System.out.println(count);
    }

}
class AccountRowMapper implements RowMapper<Account>{
    /**
     * 把结果集中数据封装到account中，然后spring把每个account封装到集合中
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setName(resultSet.getString("name"));
        account.setMoney(resultSet.getFloat("money"));
        return account;
    }
}
