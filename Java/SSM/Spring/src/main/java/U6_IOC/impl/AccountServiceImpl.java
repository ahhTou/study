package U6_IOC.impl;

import U6_IOC.dao.IAccountDao;
import U6_IOC.dao.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
 *  需要在bean.xml中配置扫描
 *
 * 1.用户创建对象的注解
 * 他们的左右就和xml配置文件中编写一个<bean>标签实现的功能是一样的
 *
 * @Component: 作用：用于把当前类对象存入spring容器中
 * 属性：
 * value 用于指定bean 的 id
 * 默认值是类名id首字母小写
 * @Controller  一般用于表现层
 * @Service     一般用于业务层
 * @Repository  一般用于持久层
 * 以上注解他们的作用和属性和Component是一模一样的
 * 他们三个是Spring框架为我们体用明确的三层使用
 * 使我们三层对象更加清晰
 * -
 * -
 * -
 * 2.用于注入数据的注解
 * 他的的作用就和在xml标签中bean标签中写一个property标签作用是一样的
 * Autowired:
 * 作用：自动按照类型注入。
 * 1. 只要容器中唯一有一个bean对象类型和要注入的变量类型匹配,就可以注入成功
 * 2. 如果ioc容器中没有任何bean的类型和要注入的变量类型匹配，则报错
 * 3. 如果有多个类型匹配时，会先按照类型匹配，再按照变量名匹配
 * Qualifier:
 * 在按照类中注入的基础之上再按照名称注入。
 * 它在给类成员注入时不能单独使用。
 * 但是在给方法参数注入时可
 * 书写位置：
 *      可以是变量上的，也可以是方法上的
 * -
 * -
 * -
 * 3.用于改变作用范围
 * 他们的作用集合bean标签中的scope属性实现的功能是一样的
 * -
 * -
 * -
 * 4.和生命周期油管
 * 他们的作用和bean标签中使用的init-method和destroy-method的作用是一样的
 */
@Component(value = "accountService")
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    public AccountServiceImpl() {
        System.out.println("对象创建了");
    }

    public void saveAccount() {
        accountDao.saveAccount();
    }
}
