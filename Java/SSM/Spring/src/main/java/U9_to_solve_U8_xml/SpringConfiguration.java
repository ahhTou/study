package U9_to_solve_U8_xml;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

/**
 * 该类是一个配置类，他的作用和bean.xml是一样的
 * Spring中的新注解
 *
 * @Configuration 作用：指定当前类是一个配置类
 * 细节：当配置类作为AnnotationConfigApplicationContext对象创建参数时，该注解可以不写
 * -
 * @ComponentScan 作用：通过注解指定spring在创建容器是要扫描的包
 * 属性：
 * value 他和 basePackages作用是一样的
 * 都是用指定创建容器时，要扫描的包
 * 我们使用此注解就相当于在xml中配置了：
 * <context:component-scan base-package="U8_demo_IOC" />
 * -
 * -
 * @Bean 作用：用于把当前方法的返回值作为bean对象存入ioc的容器中
 * 属性：
 * name:用于指定bean的id，默认值，当前方法的名称
 * 细节：细节当我们使用注解配置方法是，如果有参数，spring框架回去容器中查找有用的bean对象
 * 查找方式和Autowired注解的作用是一样的
 *
 * @Import 作用：用于导入其他的配置类
 * 属性：
 * value 用于指定其他配置类的字节码
 * 当我们使用Import的注解后，有Import注解的类就是父配置类，而导入的都是子配置类
 * @PropertySource
 * 作用：用于指定properties文件的位置
 * 属性：
 * value:指定文件的名称和路径
 * 关键字：classpath标识类路径下
 *
 *
 */
@Configuration
//@ComponentScan(basePackages = {"U9_to_solve_U8_xml"})
//@ComponentScan(basePackages = "U9_to_solve_U8_xml")
@ComponentScan("U9_to_solve_U8_xml")
@Import(JdbcConfig.class)
@PropertySource("classpath:jdbcConfig.properties")
public class SpringConfiguration {

}
