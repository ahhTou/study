package U3_DynamicSQL;

import U3_DynamicSQL.bean.Department;
import U3_DynamicSQL.bean.Employee;
import U3_DynamicSQL.dao.DepartmentMapper;
import U3_DynamicSQL.dao.EmployeeMapper;
import U3_DynamicSQL.dao.EmployeeMapperDynamicSQL;
import U3_DynamicSQL.dao.EmployeeMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MybatisTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testDynamic() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);

        Employee employee = new Employee();
        employee.setEmail("9@qq.com");
        employee.setId(13);
//        System.out.println(mapper.getEmpSByConditionIf(employee));

//        System.out.println(mapper.getEmpSByConditionTrim(employee));

//        System.out.println(mapper.getEmpSByConditionChoose(employee));

//        mapper.updateEmpSet(employee);

//        mapper.updateEmpTrimSet(employee);

/*        ArrayList<Integer> ids = new ArrayList<Integer>();
        ids.add(13);
        ids.add(14);
        ids.add(15);

        System.out.println(ids);

        System.out.println(mapper.getEmpSByConditionForeach(ids));*/

/*        Employee e1 = new Employee(null, "lastKick", "0", "1@qq.com", new Department(1));
        Employee e2 = new Employee(null, "lastWo", "1", "2@qq.com", new Department(1));
        List<Employee> employees = Arrays.asList(e1, e2);

        mapper.addEmpS(employees);*/

        Employee employee1 = new Employee();
        employee1.setLastName("a");


        System.out.println(mapper.getEmpSTestInnerParam(employee1));


        openSession.commit();

        openSession.close();
    }

}