package U4_Cache;

import U4_Cache.bean.Employee;
import U4_Cache.dao.EmployeeMapper;
import U4_Cache.dao.EmployeeMapperDynamicSQL;
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

    /**
     * 二级缓存
     * 一级缓存：（本地缓存）：sqlSession级别的缓存。一 级缓存是一 直开启的
     * 与数据库同一次会话期间查询到的数据会放在本地缓存中。
     * 以后如果需要获取相同的数据，直接从缓存中拿，没必要再去查询数据库;
     * <p>
     * 一级缓存失效情况(没有使用到当前一-级缓存的情况，效果就是，还需要再向数据库发出查询)
     * 1、sqlSession不同
     * 2、sqlSession相同，查询条件不同
     * 3、sqlSession相同，两次查询之间执行了增删改操作(这次增删改可能对当前数据有影响)
     * 4、sqlSession相同,手动清空缓存 openSession.clearCache();
     * 。
     * <p>
     * 二级缓存：（全局缓存）：基于namespace级别的缓存,一个namespace对应一个二级缓存
     * 工作机制：
     * 1、一个会话，查询一条数据，这个数据就会被放在当前会话的一级缓存中;
     * 2、如果会话关闭; - -级缓存中的数据会被保存到二级缓存中;新的会话查询信息，就可以参照二级缓存
     * 3、sqlSession   ===    EmployeeMapper==> Employee
     * DepartmentMapper=== >Department
     * 不同namespace查出的数据会放在自己对应的缓存中(map)
     * <p>
     * 使用：
     * 1. 开启全局二级缓存：<setting name="cacheEnabled" value="true"/>
     * 2. 去mapper.xml中配置使用二级缓存    <cache></cache>
     * 3. 我们POJO需要事先序列化接口
     *
     * @throws IOException
     */

    @Test
    public void test() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession openSession = sqlSessionFactory.openSession();


        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee empById = mapper.getEmpById(13);
            openSession.close();
            openSession = sqlSessionFactory.openSession();
            mapper = openSession.getMapper(EmployeeMapper.class);
            Employee empById2 = mapper.getEmpById(13);

            System.out.println(empById == empById2);

        } finally {
        }
    }

    @Test
    public void testSecondLevelCache() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession openSession = sqlSessionFactory.openSession();

        SqlSession openSession2 = sqlSessionFactory.openSession();

        try {
            EmployeeMapper mapper1 = openSession.getMapper(EmployeeMapper.class);
            EmployeeMapper mapper2 = openSession2.getMapper(EmployeeMapper.class);

            Employee empById = mapper1.getEmpById(13);

            openSession.close();

            Employee empById1 = mapper2.getEmpById(14);

            System.out.println("相同 -> " + (empById == empById1));

        } finally {
            openSession2.close();
        }
    }


    @Test
    public void testFirstLevelCache() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession openSession = sqlSessionFactory.openSession();

        SqlSession openSession2 = sqlSessionFactory.openSession();

        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            EmployeeMapper mapper2 = openSession2.getMapper(EmployeeMapper.class);

            Employee empById = mapper.getEmpById(13);
            Employee empById1 = mapper.getEmpById(13);

            openSession.clearCache();

            Employee empById2 = mapper2.getEmpById(13);

            System.out.println("第一次结果和第二次结果是否相同 -> " + (empById == empById1));

            System.out.println("不同的openSession的查询结果 -> " + (empById2 == empById1));

        } finally {
            openSession.close();
        }

    }

}