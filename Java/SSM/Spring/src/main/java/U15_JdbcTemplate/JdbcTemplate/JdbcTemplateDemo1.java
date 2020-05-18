package U15_JdbcTemplate.JdbcTemplate;

import com.mchange.v2.c3p0.DriverManagerDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.DriverManager;

public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
        // 1.准备数据源 ： spring内置数据源
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClass("com.mysql.cj.jdbc.Driver");
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/eesy?serverTimezone=GMT%2B8");
        ds.setUser("root");
        ds.setPassword("123");

        // 2. 创建JdbcTemplate对象
        JdbcTemplate jt = new JdbcTemplate();

        // 3.给jt设置数据源
        jt.setDataSource(ds);

        // 4. 执行操作
        jt.execute("insert into account(name, money) values('ccc', 1000)");
    }
}
