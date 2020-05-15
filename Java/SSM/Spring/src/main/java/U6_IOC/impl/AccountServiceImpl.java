package U6_IOC.impl;

import U6_IOC.dao.IAccountDao;
import U6_IOC.dao.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * <bean
 * id="accountService"
 * class="U3_KonwSpring.impl.AccountServiceImpl"
 * scope=""
 * init-method=""
 * destroy-method=""
 * >
 * <property
 * name=""
 * value="" | ref=""
 * ></property>
 *
 * </bean>
 * 需要在bean.xml中配置扫描
 * -
 * <p>
 * 1.用户创建对象的注解
 * 他们的左右就和xml配置文件中编写一个<bean>标签实现的功能是一样的
 *
 * @Component: 作用：用于把当前类对象存入spring容器中
 * 属性：
 * value 用于指定bean 的 id
 * 默认值是类名id首字母小写
 * @Controller 一般用于表现层
 * @Service 一般用于业务层
 * @Repository 一般用于持久层
 * 以上注解他们的作用和属性和Component是一模一样的
 * 他们三个是Spring框架为我们体用明确的三层使用
 * 使我们三层对象更加清晰
 * -
 * -
 * -
 * 2.用于注入数据的注解
 * 他的的作用就和在xml标签中bean标签中写一个property标签作用是一样的
 * <p>
 * @Autowired: 作用：自动按照类型注入。
 * 1. 只要容器中唯一有一个bean对象类型和要注入的变量类型匹配,就可以注入成功
 * 2. 如果ioc容器中没有任何bean的类型和要注入的变量类型匹配，则报错
 * 3. 如果有多个类型匹配时，会先按照类型匹配，再按照变量名匹配
 * * 书写位置：
 * *      可以是变量上的，也可以是方法上的
 * @Qualifier: 在按照类中注入的基础之上再按照名称注入。
 * 它在给类成员注入时不能单独使用。
 * 但是在给方法参数注入时可
 * 属性：
 * value :用于注入bean的id
 * 必须和Autowired配合
 * @Resource
 * 作用： 直接按照bean的id注入，可以独立使用
 * 属性：
 * name：用于指定bean的id
 * <p>
 * 以上三个注入都只能注入其他bean类型的数据，而基本类型和String类型无法使用上述注解实现。
 * 另外，集合类型的注入只能通过xml来实现
 * @Value 作用：用于注入基本类型和String类型的数据
 * 属性：
 * value：用于指定数据的值，他可以使用Spring 中 的SpEl(Spring中的el表达式)
 * SpEl的表达式：${表达式}
 * -
 * -
 * -
 * 3.用于改变作用范围
 * 他们的作用集合bean标签中的scope属性实现的功能是一样的
 * -@Scope
 * 作用：用于指定bean的作用范围
 * 属性
 * value:指定范围的取值，常用取值：singleton, prototype [默认 单例]
 * -
 * -
 * 4.和生命周期有关
 * 他们的作用和bean标签中使用的init-method和destroy-method的作用是一样的
 * PreDestroy
 * 指定销毁方法
 * PostConstruct
 * 用于指定初始化方法
 */
@Component(value = "accountService")
//@Scope("prototype")
public class AccountServiceImpl implements IAccountService {

    //    @Autowired
//    @Qualifier("accountDao1")
    @Resource(name = "accountDao1")
    private IAccountDao accountDao;

    public AccountServiceImpl() {
        System.out.println("对象创建了");
    }

    public void saveAccount() {
        accountDao.saveAccount();
    }

    @PostConstruct
    public void init() {
        System.out.println("初始化");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("销毁");
    }
}
