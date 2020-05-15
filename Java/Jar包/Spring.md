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