### Spring的配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--创建约束-->
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">
</beans>
```

### Bean

```xml
    <bean id="" 				//调用标识符
          class=""				//
          factory-method=""		//调用工厂内方法的名称
          scope=""				//作用于
          init-method=""		//创建时调用的函数
          destroy-method=""		//销毁时调用的函数
    />
```



#### 创建方式

##### 使用默认的构造函数创建对象

> 在spring的配置文件中使用bean标签，配以id和class属性和标签之后
> 采用的就是默认构造函数创建bean对象，此时如果类中没有默认构造函数
> 则对象无法创建

main

```java
ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("U4_bean.xml");
IAccountService as1 = (IAccountService) ac.getBean("accountService");
```

xml

```xml
    <bean
            id="accountService"
            class="U4_Bean.impl.AccountServiceImpl"
    />
```



##### 使用普通工厂中的方法创建对象

> 使用某个类中的方法创建对象，并存入spring容器
> 相当于：实例化一个工厂，然后调用

factory

```java
public class InstanceFactory {
    public AccountServiceImpl getAccountService() {
        System.out.println("方法2");
        return new AccountServiceImpl();
    }
}
```

xml

```xml
<bean id="instanceFactory" class="U4_Bean.factory.InstanceFactory"/>
<bean id="accountService2" factory-bean="instanceFactory"
          factory-method="getAccountService"/>
```

##### 使用工厂中的静态方法创建对象

> ```
> 使用某个类中的静态方法创建对象，并存入spring容器
> 相当于：因为是静态方法，所以无需调用
> ```

factory

```java
public class StaticFactory {
    public static AccountServiceImpl getAccountService() {
        System.out.println("方法3");
        return new AccountServiceImpl();
    }
}
```

xml

```xml
<bean id="accountService3" class="U4_Bean.factory.StaticFactory"
      factory-method="getAccountService"
      scope="prototype"
/>
```

#### 作用范围

> ```
> singleton:单例的（默认）
> prototype:多例的
> request:作用于web应用的请求范围
> session:作用web应用的会话返回
> global-session: 作用于集群环境的会话范围（全局会话范围） 当不是集群范围时，他就是session
> ```

#### 生命周期

|      | 多例对象                                                     | 单例对象                     |
| ---- | ------------------------------------------------------------ | ---------------------------- |
| 出生 | 当我们使用spring框架为我们创建对象时                         | 当容器创建对象时出生         |
| 活着 | 对象只要是在使用过程中就一直存活                             | 容器还在，对象就活着         |
| 死亡 | 当对象长时间不用，且没有别的对象引用是，由java垃圾回收器回收 | 容器销毁                     |
| 总结 | 和普通对象生命周期类似                                       | 单例对象的生命周期和容器相同 |



### DI 依赖注入

> 依赖注入	 				Dependency Injection
> IOC的作用  				降低程序间的耦合
> 依赖关系的管理	 	以后都交给了spring
>
> 在当前类需要用到其他其他类的对象,由spring为我们提供,我们只需要在配置文件中生命, 依赖关系的维护,就称为依赖注入

| 能注入的数据       | 能注入的方式     |
| ------------------ | ---------------- |
| 基本类型和string   | 使用构造函数提供 |
| 其他bean类型       | 使用set方法提供  |
| 复杂类型/ 集合类型 | 使用注解提供     |

#### 构造函数注入

##### 基本

|   使用的标签    | 标签出现的位置 |
| :-------------: | :------------: |
| constructor-arg | bean标签的内部 |

##### 标签中的属性

| 标签     | 属性                                                         |
| -------- | ------------------------------------------------------------ |
| type     | 用于指定要注入的数据的数据类型，该数据类型也是构造函数中某个或某些参数的类型 |
| index    | 用于指定要注入的数据给构造函数中指定的索引位置的参数赋值，索引从0开始 |
| **name** | **用于指定给构造函数中指定名称的参数赋值**                   |
| value    | 用于提供基本类型和String类型的数据                           |
| ref      | 用于指定其他bean类型，他值得就是在spirng的Ioc核心容器中出现的bean对象 |

##### 优势和弊端

| 利弊 |                                                              |
| ---- | ------------------------------------------------------------ |
| 优势 | 在获取bean对象时，注入数据是必须的操作，否则对象无法创建成功。 |
| 弊端 | 变了bean对象的实例化方式，使我们在创建对象时，如果用不到这些数据，也必须提供。 |

##### 实例

impl

```java
private String name;
private Integer age;
private Date birthday;

public AccountServiceImpl(String name, Integer age, Date birthday) {
    this.name = name;
    this.age = age;
    this.birthday = birthday;
}
```



```xml
<bean
        id="accountService"
        class="U5_DI.impl.AccountServiceImpl"
>
    <constructor-arg name="name" value="ahhTou" />
    <constructor-arg name="age" value="20" />
    <constructor-arg name="birthday" ref="now" />
</bean>
<!--    配置一个日期对象-->
<bean id="now" class="java.util.Date"/>
```

#### Set方法注入

##### 基本

| 使用的标签 | 标签出现的位置 |
| :--------- | -------------- |
| property   | bean标签的内部 |

##### 标签中的属性

| 标签  | 属性                                                         |
| ----- | ------------------------------------------------------------ |
| name  | 用于指定给构造函数中指定名称的参数赋值                       |
| value | 用于提供基本类型和String类型的数据                           |
| ref   | 用于指定其他bean类型，他值得就是在spirng的Ioc核心容器中出现的bean对象 |

##### 优势和弊端

| 利弊 |                                                              |
| ---- | ------------------------------------------------------------ |
| 优势 | 在获取bean对象时，注入数据是必须的操作，否则对象无法创建成功。 |
| 弊端 | 如果有某个成员必须有值，则set方法无法保证一定注入。          |

##### 实例

impl

```java
private String name;
private Integer age;
private Date birthday;
public void setName(String name) {
    this.name = name;
}
public void setAge(Integer age) {
    this.age = age;
}

public void setBirthday(Date birthday) {
    this.birthday = birthday;
}
```

xml

```java
<bean
        id="accountService2"
        class="U5_DI.impl.AccountServiceImpl2"
>
    <property name="name" value="ahhTou"/>
    <property name="age" value="20"/>
    <property name="birthday" ref="now"/>
</bean>
<!--    配置一个日期对象-->
<bean id="now" class="java.util.Date"/>
```

#### 复杂类型/ 集合类型 的注入

##### 特点

> 结构相同，标签可以互换

可以互换标签

| 用于给list结构集合注入的标签 | 用于map结构的集合注入的标签 |
| ---------------------------- | --------------------------- |
| list array set               | map props                   |

impl

```java
public String[] getMyStr() {
    return myStr;
}

public void setMyStr(String[] myStr) {
    this.myStr = myStr;
}

public void setMyList(List<String> myList) {
    this.myList = myList;
}

public void setMySet(Set<String> mySet) {
    this.mySet = mySet;
}

public void setMyMap(Map<String, String> myMap) {
    this.myMap = myMap;
}

public void setMyProps(Properties myProps) {
    this.myProps = myProps;
}

private String[] myStr;
private List<String> myList;
private Set<String> mySet;
private Map<String, String> myMap;
private Properties myProps;


public void saveAccount() {
    System.out.println(Arrays.toString(myStr));
    System.out.println(myList);
    System.out.println(mySet);
    System.out.println(myMap);
    System.out.println(myProps);
}
```

xml

```xml
<bean
        id="accountService3"
        class="U5_DI.impl.AccountServiceImpl3"
>
    <property name="myStr">
        <array>
            <value>AAA</value>
            <value>BBB</value>
            <value>CCC</value>
        </array>
    </property>

    <property name="myList">
        <array>
            <value>AAA</value>
            <value>BBB</value>
            <value>CCC</value>
        </array>
    </property>
    <property name="mySet">
        <array>
            <value>AAA</value>
            <value>BBB</value>
            <value>CCC</value>
        </array>
    </property>

    <property name="myMap">
        <map>
            <entry key="testA" value="aaa"/>
            <entry key="testB">
                <value>BBB</value>
            </entry>
        </map>
    </property>

    <property name="myProps">
        <props>
            <prop key="testD">DDD</prop>
            <prop key="testC">CCC</prop>
        </props>
    </property>
```

### IOC 注解

#### Spring中IOC的常用注解

##### 准备

> 需要在bean.xml中撇子

xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

    <!--        告知spring创建容器时，要扫描的包
            配置所需要的标签不是在beans的约束中，而是一个名为
            context名称空间和约束中
        -->
    <context:component-scan base-package="U6_IOC">

    </context:component-scan>
</beans>
```

##### 用户创建对象的注解

| 注解        | 作用                             | 属性                     |
| ----------- | -------------------------------- | ------------------------ |
| @Component  | 用于把当前类对象存入spring容器中 | value 用于指定bean 的 id |
| @Controller | 同上，一般用于表现层             |                          |
| @Service    | 同上，一般用于业务层             |                          |
| @Repository | 同上，一般用于持久层             |                          |

> 以上注解他们的作用和属性和Component是一模一样的
>
>  * 他们三个是Spring框架为我们体用明确的三层使用
>  * 使我们三层对象更加清晰

实例

```java
@Component(value = "accountService")
public class AccountServiceImpl implements IAccountService {
}

@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {
}
```

##### 用于注入数据的注解

| 注解       | 作用                                                         | 属性                                                         |
| ---------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| @Autowired | 自动按照类型注入。 <br /> 1. 只要容器中唯一有一个bean对象类型和要注入的变量类型匹配,就可以注入成功 <br />2. 如果ioc容器中没有任何bean的类型和要注入的变量类型匹配，则报错 *<br />3. 如果有多个类型匹配时，会先按照类型匹配，再按照变量名匹配 |                                                              |
| @Qualifier | 在按照类中注入的基础之上再按照名称注入。它在给类成员注入时不能单独使用。 但是在给方法参数注入时可 | value :用于注入bean的id *<br />必须和Autowired配合           |
| @Resource  | 直接按照bean的id注入，可以独立使用                           | name：用于指定bean的id                                       |
| @Value     | 作用：用于注入基本类型和String类型的数据                     | * value：用于指定数据的值，他可以使用Spring 中 的SpEl(Spring中的el表达式) <br />*SpEl的表达式：${表达式} |

实例

```java
	@Autowired
	@Qualifier("accountDao1")
	private IAccountDao accountDao;

// 效果相同

    @Resource(name = "accountDao1")
    private IAccountDao accountDao;
```

##### 用于改变作用范围

> 他们的作用集合bean标签中的scope属性实现的功能是一样的

实例

```java
@Component(value = "accountService")
@Scope("prototype")
public class AccountServiceImpl implements IAccountService {
}
```

##### 和生命周期有关

> 他们的作用和bean标签中使用的init-method和destroy-method的作用是一样的

| @PreConstruct | @PreDestroy        |
| ------------- | ------------------ |
| 指定销毁方法  | 用于指定初始化方法 |

实例

```java
@PostConstruct
public void init() {
    System.out.println("初始化");
}

@PreDestroy
public void destroy() {
    System.out.println("销毁");
}
```

#### 案例中使用xml和注解方式实现单表的CRUD操作

> 参照 U8_demo_IOC 包下 

#### 改造基于注解的IOC的案例,使用纯注解的方式实现

> 参照 U9_to_solve_U8_xml 下

##### 准备

> 创建一个类 如`SpringConfiguration`

```java
@Configuration
@ComponentScan("U9_to_solve_U8_xml")
@Import(JdbcConfig.class)
@PropertySource("classpath:jdbcConfig.properties")
public class SpringConfiguration {
}
```

修改获得容器的方式,从xml改为class引入

```java
	ApplicationContext ac = new ClassPathXmlApplicationContext("U9_bean.xml");
	
	ApplicationContext ac = new
            AnnotationConfigApplicationContext(SpringConfiguration.class);
	//	AnnotationConfigApplicationContext 可以传入多个config类文件
```

##### 全部注解

| 注解            | 作用                                              | 属性                                                         | 细节                                                         |
| --------------- | ------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| @Configuration  | 指定当前类是一个配置类                            |                                                              | 配置类作为AnnotationConfigApplicationContext对象创建参数时，该注解可以不写 |
| @ComponentScan  | 通过注解指定spring在创建容器是要扫描的包          | value 和 basePackages作用是一样的                            |                                                              |
| @Bean           | 用于把当前方法的返回值作为bean对象存入ioc的容器中 | name:用于指定bean的id，默认值，当前方法的名称                | 细节当我们使用注解配置方法是，如果有参数，spring框架回去容器中查找有用的bean对象， 查找方式和Autowired注解的作用是一样的 |
| @Import         | 用于导入其他的配置类                              | value 用于指定其他配置类的字节码                             | 当我们使用Import的注解后，有Import注解的类就是父配置类，而导入的都是子配置类 |
| @PropertySource | 用于指定properties文件的位置                      | value:指定文件的名称和路径<br />关键字：classpath标识类路径下 |                                                              |

##### 替代扫描

| 场景 | 写法                                                    |
| ---- | ------------------------------------------------------- |
| 单个 | @ComponentScan(basePackages = "package1")               |
| 多个 | @ComponentScan(basePackages = {"package1", "package2"}) |
| 简写 | @ComponentScan("package1")                              |

##### 替代注入

> 函数中有参数，Spirng会根据参数名自动注入

xml

```xml
<!--    配置QueryRunner-->
<bean id="runner" class="org.apache.commons.dbutils.QueryRunner" scope="prototype">
    <!--        注入数据源-->
    <constructor-arg name="ds" ref="dataSource"/>
</bean>

<!--    配置数据源-->
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
    <property name="driverClass" value="com.mysql.cj.jdbc.Driver"/>
    <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/eesy?serverTimezone=GMT%2B8"/>
    <property name="user" value="root"/>
    <property name="password" value="123"/>
</bean>
```

class

```java
@Bean(name = "runner")
@Scope("prototype")
public QueryRunner createQueryRunner(DataSource dataSource) {
    return new QueryRunner(dataSource);
}

@Bean(name = "dataSource")
public DataSource createDataSource() {
    ComboPooledDataSource ds = new ComboPooledDataSource();
    try {
        ds.setDriverClass(driver);
        ds.setJdbcUrl(url);
        ds.setUser(username);
        ds.setPassword(password);

    } catch (Exception e) {
        e.printStackTrace();
    }
    return ds;
}
```

##### 导入其他配置类

```java
@Import(JdbcConfig.class)
```

导入配置文件

主配置类

```java
@PropertySource("classpath:jdbcConfig.properties")
```

使用中

```java
@Value("${jdbc,driver}")
private String driver;

@Value("${jdbc.url}")
private String url;

@Value("${jdbc.username}")
private String username;

@Value("${jdbc.password}")
private String password;
```

#### spring 和 Junit的整合

##### 准备

pom.xml

```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
</dependency>
```

##### 说明

> Spring整合junit的配置
> 1.导入spring整合junit的jar包（坐标）
> 2.使用Junit提供的一个注解把原有的注解，替换成Spring提供的


> **当我们使用spring 5.x版本的时候，要求junit的jar包必须是4.12及以上**

##### 注解

| @Runwith                                                     | @ContextConfiguration                                        |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| 告知spring运行期，spring和ioc创建是基于xml还是注解，并说明位置 | locations：指定xml文件的位置，加上classpath关键字，标识在该类下 |
|                                                              | classes：指定注解类所在的位置                                |

```java
// 基于ioc
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
    @Autowired
    private final IAccountService as = null;
```

##### 替代

```java
ApplicationContext ac = new
        AnnotationConfigApplicationContext(SpringConfiguration.class);
IAccountService as = ac.getBean(
        "accountService",
        IAccountService.class
);
```

```java
@Autowired
private final IAccountService as = null;
```

### AOP

#### 完善案例

> 参照 U10_to_improve_U7 
>
> 添加 转账功能

#### 分析案例问题

> 多条Sql语句创建多个连接，导致数据不安全
>
> 使用SQL事务管理，导致代码冗余
>
> 解决办法：动态代理

#### 回顾之前讲过的一个技术：动态代理

> 动态代理
>
> 特点：字节码随用随创建，随用随加载
>
> 作用：不修改源码上的基础上对方法增强

|                    | 基于接口的动态代理              | 基于子类接口的动态代理 |
| ------------------ | ------------------------------- | ---------------------- |
| 来源               | JDK官网                         | 第三方cglib库          |
| 涉及的类           | Proxy                           | Enhancer               |
| 如何创建代理对象   | 使用Proxy类中的newProxyInstance | 使用Enhancer中的create |
| 创建代理对象的要求 | 被代理类不能是最终类            | 被代理类不能是最终类   |

#####  基于接口的动态代理

参数

| 类名              | 描述               | 作用                                                         |
| ----------------- | ------------------ | ------------------------------------------------------------ |
| ClassLoader       | 类加载器           | 用于加载代理对象字节码。和被代理对象使用相同的类加载器。固定写法 |
| Class[]           | 字节码数组         | 用于让代理对象和被代理对象有相同的方法。固定写法             |
| InvocationHandler | 用于提供增强的代码 | 他是让我们如何鞋代理。我们一般都是写一个该类的实现类 *      通常都是匿名内部类 *      此接口的实现类都是谁用谁写 |

| InvocationHandler的参数 | 描述                         |
| ----------------------- | ---------------------------- |
| Object proxy            | 代理对象的引用               |
| Method method           | 当前执行的方法               |
| Object[] args           | 当前执行方法所需的参数       |
| return                  | 和当前代理对象有相同的返回值 |

实例

```java
// 1.创建被代理对象实例
final Producer producer = new Producer();

// 2.创建代理对象
IProducer proxyProducer = (IProducer) Proxy.newProxyInstance(
    
    // ClassLoader
    producer.getClass().getClassLoader(),
    // Class[]
    producer.getClass().getInterfaces(),
    
    // InvocationHandler
        new InvocationHandler() {
            /**
             * 作用，被代理对象的所有接口方法都会经过该方法
             * @param proxy     代理对象的引用
             * @param method    当前执行的方法
             * @param args      当前执行方法所需的参数
             * @return 和当前代理对象有相同的返回值
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 提供增强的代码
                Object returnValue = null;
                // 1.获取方法的执行参数
                Float money = (Float) args[0];
                // 2.判断当前方法是不是销售
                if ("saleProduct".equals(method.getName())) {
                    returnValue = method.invoke(producer, money*0.8f);
                }
                return returnValue;
            }
        }
);

// 3.使用代理方法
proxyProducer.saleProduct(10000f);
```

##### 基于子类接口的动态代理

参数

| 类名     | 描述               | 作用                                                         |
| -------- | ------------------ | ------------------------------------------------------------ |
| Class    | 字节码             | 指定被代理对象的字节码                                       |
| Callback | 用于提供增强的代码 | 他是让我们如何鞋代理。我们一般都是写一个该类的实现类，通常都是匿名内部类，此接口的实现类都是谁用谁写，我们一般写的都是该接口的子接口实现类MethodInterceptor |

| InvocationHandler的参数 | 描述                   |
| ----------------------- | ---------------------- |
| Object proxy            | 代理对象的引用         |
| Method method           | 当前执行的方法         |
| Object[] args           | 当前执行方法所需的参数 |
| MethodProxy methodProxy | 当前执行的代理对象     |

实例

```java
final Producer producer = new Producer();
		// 1.创建被代理子类的实例
        final Producer producer = new Producer();

		// 2.创建代理
        Producer cglibProducer =  (Producer) Enhancer.create(
                producer.getClass(),
                new MethodInterceptor() {
                    /**
                     * 执行被代理对象的任何方法都会经过该方法
                     * @param proxy
                     * @param method
                     * @param args
                     * 以上参数和基于接口的动态代理中的invoke方法的参数是一样的
                     * @param methodProxy 当前执行的代理对象
                     * @return
                     * @throws Throwable
                     */
                    @Override
                   public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                        // 提供增强的代码
                        Object returnValue = null;
                        // 1.获取方法的执行参数
                        Float money = (Float) args[0];
                        // 2.判断当前方法是不是销售
                        if ("saleProduct".equals(method.getName())) {
                            returnValue = method.invoke(producer, money*0.8f);
                        }
                        return returnValue;
                    }
                }

        );
		
		// 3.使用
        cglibProducer.saleProduct(12000f);
```

#### 动态代理的一个实现方式

pom.xml

```xml
<dependency>
    <groupId>cglib</groupId>
    <artifactId>cglib</artifactId>
    <version>2.1_3</version>
</dependency>
```



#### 解决案例中的问题

> 使用动态代理

#### AOP的概念

#### Spring中的AOP相关术语

#### Spring中基于XML的注解和AOP配置

##### 基本配置

xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd">
</beans>
```

pom.xml

```xml
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.7.4</version>
</dependency>
```



##### 基本结构

```xml
<!-- 配置AOP -->
<aop:config>
    
    <!-- 配置切面表达式 -->
    <aop:pointcut id="pt1" expression="execution(*  U12_AOP.service.impl.*.*(..))"/>
    
    <!-- 配置切面 -->
    <aop:aspect id="logAdvice" ref="logger">
    
        <!-- 配置通知类 -->
        <aop:before method="beforePrintLog" pointcut-ref="pt1"/>
        <aop:after-returning method="afterReturnPrintLog" pointcut-ref="pt1"/>
        <aop:after-throwing method="afterThrowingPrintLog" pointcut-ref="pt1"/>
        <aop:after method="afterPrintLog" pointcut-ref="pt1"/>
    
    </aop:aspect>
    
</aop:config>
```

##### 切入点表达式

| 属性   | 内容                                            |
| ------ | ----------------------------------------------- |
| 关键字 | execution(表达式)                               |
| 表达式 | 访问修饰符   返回值     包名.包名...类名.方法名 |

```
标准的表达式方法写法:
    public void U12_AOP.service.impl.IAccountServiceImpl.saveAccount()
    访问修饰符可以省略
        void U12_AOP.service.impl.IAccountServiceImpl.saveAccount()
    返回值可以试用通配符，表示返回任意值
        * U12_AOP.service.impl.IAccountServiceImpl.saveAccount()
    包名可以试用通配符，表示任意包,但是有几级包就要几个星
        * *.*.*.IAccountServiceImpl.saveAccount()
    包名可以试用..表示当前包以及其子包
        * *..IAccountServiceImpl.saveAccount()
    类名和方法名都可以试用星来表示
        * *..*.saveAccount()
        * *..*.*()
    参数列表
        可以直接写数据类型
        * *..*.*(int)
            基本类型直接写名称          -> int
            引用类新写包名.类名的方式     -> java.lang.String
        可以试用通配符表示任意类型，但必须有参数
        * *..*.*(*)
        可以试用..表示有无参数均可,有参数可以是任意类型
        * *..*.*(..)
全通配写法：
    * *..*.*(..)

实际开发中切入点表达式的通常写法：
    切到业务类下的所有方法
    *  U12_AOP.service.impl.*.*(..)
```

##### 常用标签

| 标签                | 作用             | 属性                                                         |
| ------------------- | ---------------- | ------------------------------------------------------------ |
| aop:config          | 配置AOP          | 包含多个 aop:aspect                                          |
| aop:aspect          | 配置切面         | 包含以下所有<br />id属性：给切面一个提供一个唯一标识 <br />ref属性：指定通知类bean的ID |
| aop:pointcut        | 配置切入点表达式 | id：唯一标识符<br />expression：指定表达式的内容             |
| aop:before          | 前置通知         | method、pointcut、pointcut-ref                               |
| aop:after-returning | 后置通知         | method、pointcut、pointcut-ref                               |
| aop:after-throwing  | 异常通知         | method、pointcut、pointcut-ref                               |
| aop:after           | 最终通知         | method、pointcut、pointcut-ref                               |
| aop:around          | 环绕通知         | method、pointcut、pointcut-ref                               |



##### 标签属性

###### aop:aspect

| id                         | ref                |
| -------------------------- | ------------------ |
| 给切面一个提供一个唯一标识 | 指定通知类bean的ID |

```xml
<!-- 通知bean类 -->
<bean id="logger" class="U12_AOP.utils.logger">

<aop:config>
    <aop:aspect id="logAdvice" ref="logger">
    </aop:aspect>
</aop:config>
```



###### aop:pointcut

| id                   | expression       |
| -------------------- | ---------------- |
| 指定表达式的唯一标识 | 指定表达式的内容 |

```xml
<aop:pointcut id="pt1" expression="execution(*  U12_AOP.service.impl.*.*(..))"/>
```

>             此标签写在as:aspect标签只能当前切面使用
>             它还可以写在aop:aspect外面，此时就变成了所有切面可用

###### 通知类

| method       | pointcut         | pointcut-ref         |
| ------------ | ---------------- | -------------------- |
| 提供方法名称 | 提供切入点表达式 | 提供aop:pointcut的id |

```xml
<!-- 讲切入点表达式写在内部 -->
<aop:before method="beforePrintLog" pointcut="execution(* *..*.*(..))"/>

<!-- 讲切入点表达式写在外部部 -->
<aop:before method="beforePrintLog" pointcut-ref="pt1"/>
<aop:pointcut id="pt1" expression="execution(*  U12_AOP.service.impl.*.*(..))"/>

```

##### 环绕通知

> spring中的环绕通知：它是spring框架为我们]提供的一种可以在代码中手动控制增强方法何时使用的方式

| 使用接口            | 使用接口的方法 |
| ------------------- | -------------- |
| ProceedingJoinPoint | proceed()      |

logger

```java
public Object aroundPrintLog(ProceedingJoinPoint pjb) {
    Object reValue = null;
    
    try {
        Object[] args = pjb.getArgs();
        System.out.println("-> 前置通知");
        
        // 方法
        reValue = pjb.proceed(args);

        System.out.println("-> 后置通知");
        return reValue;
    } catch (Throwable throwable) {
        System.out.println("-> 异常通知");
        throw new RuntimeException(throwable);

    } finally {
        System.out.println("-> 最终通知");
    }
}
```

```xml
<aop:around method="aroundPrintLog" pointcut-ref="pt1" />
```