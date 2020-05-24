package U1_helloWorld.test;

import U1_helloWorld.bean.Employee;
import U1_helloWorld.dao.EmployeeMapper;
import U1_helloWorld.dao.EmployeeMapperAnnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void test() throws IOException {
        /**
         * 1.根据xml配置文件（全局配置文件） 创建一个SqlSessionFactory对象
         *  有数据源和一些运行环境信息
         * 2.配置 sql映射文件，配置了每一个sql，以及sql封装规则
         * 3.将sql映射文件注册到全局配置文件中
         * 4.写代码
         *      1> 根据全局配置文件得到SqlSessionFactory
         *      2> 使用sqlSession工厂，获取sqlSession对象使用他来执行增删改查
         *          一个sqlSession就是代表和数据库的一次会话
         *      3> 使用mysql的唯一标识来告诉mybatis执行那个sql。sql都是保存在sql映射文件中
         */
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取sqlSession实例 能直接执行已经映射的sql语句
        // s -> sql的唯一标识：
        // 执行sql要用的参数
        SqlSession openSqlSession = sqlSessionFactory.openSession();

        try {
            Employee selectEmp = openSqlSession.selectOne("getEmpById", 1);

            System.out.println(selectEmp);
        } finally {
            openSqlSession.close();

        }


    }

    @Test
    public void test01() throws IOException {
        // 1. 获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2. 获取SqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();

        // 3. 获取接口实现类对象
        //      -> 回自动创建一个代理对象，代理对象去执行增删查改
        try {
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

        Employee empById = mapper.getEmpById(1);

        System.out.println(empById);

        }finally {
            openSession.close();
        }

    }

    @Test
    public void test02() throws IOException {
        System.out.println("基于注解的");
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();

        try{
            EmployeeMapperAnnotation mapper = openSession.getMapper(EmployeeMapperAnnotation.class);
            Employee empById = mapper.getEmpById(1);

            System.out.println(empById);

        }finally {
            openSession.close();
        }

    }


}
