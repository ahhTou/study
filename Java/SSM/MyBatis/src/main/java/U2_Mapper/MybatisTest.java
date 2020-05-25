package U2_Mapper;

import U2_Mapper.bean.Employee;
import U2_Mapper.dao.EmployeeMapper;
import U2_Mapper.dao.EmployeeMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }


    @Test
    public void test01() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

            Employee empById = mapper.getEmpById(1);

            System.out.println(empById);

        } finally {
            openSession.close();
        }

    }

    /**
     * 增删改
     * 1、mybatis允许增删改直接定义以下类型返回值
     * Integer、Long、Boolean
     * 直接在接口上定义
     * 2.手动提交数据 =>openSession()
     * 自动提交
     * openSession(true);
     *
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession openSession = sqlSessionFactory.openSession();


        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

            Employee employee = new Employee(null, "ahhTouProMax", "男", "12223@qq.com");
//            Employee employeeToUpdate = new Employee(14, "ahhTouProMaxPlus", "男", "12223@qq.com");


            System.out.println(mapper.getEmpByIdAndLastName(16, "ahhTouProMax"));


// 自增取值测试
//            {
//                mapper.addEmp(employee);
//
//                System.out.println(employee.getId());
//            }

//            mapper.deleteEmpById(1);

//            boolean b = mapper.updateEmp(employeeToUpdate);

//            System.out.println("更新员工" +employeeToUpdate.getId() + "更新 "+b + "了");
            // 手动提交
            openSession.commit();
        } finally {
            openSession.close();
            System.out.println("--END--");
        }
    }


    @Test
    public void test02() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

            /* 参数是list测试
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", 16);
            map.put("lastName", "ahhTouProMax");

            System.out.println(mapper.getEmpByMap(map));
            openSession.commit();

             */


            /* 返回值list测试
            List<Employee> a = mapper.getEmpByLastNameLike("%a%");

            for (Employee employee : a){
                System.out.println(employee);
            }

             */

            /* 返回一条记录的map，key是列名，值就是对应的值
            Map<String, Object> map = mapper.getEmpByIdReturnMap(13);
            System.out.println(map);
             */

            /* 多条记录封装一个map: Map< Integer , Employee>
                :key是这条记录的主键，value是记录封装后的JavaBean

            Map<Integer, Employee> a = mapper.getEmpByLastNameLikeReturnMap("a");
            System.out.println(a);

            */


        } finally {
            openSession.close();
            System.out.println("--END--");
        }
    }

    @Test
    public void test03() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession openSession = sqlSessionFactory.openSession();


        try {

            EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
            System.out.println(mapper.getEmpById(13));

        } finally {
            openSession.commit();
        }
    }

}


