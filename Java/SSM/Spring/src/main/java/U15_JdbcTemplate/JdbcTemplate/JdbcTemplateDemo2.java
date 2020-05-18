package U15_JdbcTemplate.JdbcTemplate;

import com.mchange.v2.c3p0.DriverManagerDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateDemo2 {
    public static void main(String[] args) {

        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("U15_bean.xml");

        // 2.获取对象
        JdbcTemplate jt = ac.getBean("jdbcTemplate", JdbcTemplate.class);

        // 3.执行操作
        jt.execute("insert into account(name, money) values('ddd', 1000)");

    }
}
