package U5_MBG;

import U5_MBG.bean.Employee;
import U5_MBG.bean.EmployeeExample;
import U5_MBG.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyTest {
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }


    @Test
    public void a() throws InterruptedException, SQLException, IOException, XMLParserException, InvalidConfigurationException {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("src/mbg.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
    @Test
    public void b() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession openSession = sqlSessionFactory.openSession();

        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);



//        查询所有
/*        List<Employee> employees = mapper.selectByExample(null);

        System.out.println(employees);*/

//        查询员工名字中有e的，和员工姓名是1的 或者名字叫 ahhTouPro的
//        封装员工查询条件的example

        EmployeeExample employeeExample = new EmployeeExample();
        // 创建一个Criteria ，这个 Criteria就是拼装查询条件
        EmployeeExample.Criteria criteria1 = employeeExample.createCriteria();

        criteria1.andLastNameLike("%a%");
        criteria1.andGenderEqualTo("1");

        // 添加一个或者条件
        EmployeeExample.Criteria criteria2 = employeeExample.createCriteria();
        criteria2.andLastNameLike("%ahhTouPro%");

        employeeExample.or(criteria2);

        List<Employee> employees1 = mapper.selectByExample(employeeExample);

        System.out.println(employees1);


    }

}
