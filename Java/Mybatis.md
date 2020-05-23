# MyBatis

## 准备

依赖

```xml
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.4.1</version>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.20</version>
</dependency>
```

配置文件mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis?serverTimezone=GMT%2B8"/>
                <property name="username" value="root"/>
                <property name="password" value="123"/>
            </dataSource>
        </environment>
    </environments>

    <!-- 将我们写好的sql映射文件一定要注册到全局配置文件中（mybatis-config.xml）-->
    <mappers>
        <mapper resource="U1/EmployeeMapper.xml"/>
    </mappers>
</configuration>
```

## HelloWord

### 通过唯一表示名

#### 为全局配置文件配置数据源

> <dateSource></dateSource>

```xml
<property name="driver" value="com.mysql.cj.jdbc.Driver"/>
<property name="url" value="jdbc:mysql://localhost:3306/mybatis?serverTimezone=GMT%2B8"/>
<property name="username" value="root"/>
<property name="password" value="123"/>
```

#### 配置SQL映射文件

|            | 属性                         |
| ---------- | ---------------------------- |
| namespace  | 命名空间                     |
| id         | 唯一标识                     |
| resultType | 返回值类型（可以封装成对象） |
| #{id}      | 从传递过来的参数中取出id     |

EmployeeMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="U1_helloWorld.bean.EmployeeMapper">
    <select id="selectEmp" resultType="U1_helloWorld.bean.Employee">
    select id,last_name as lastName,gender,email from tab1_employee where id = #{id}
  </select>
</mapper>
```

#### 将sql映射文件注册到全局

> <mappers></mappers>

```xml
<mapper resource="U1/EmployeeMapper.xml"/>
```

#### 写代码

```java
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
        Employee selectEmp = openSqlSession.selectOne("selectEmp", 1);

        System.out.println(selectEmp);
    } finally {
        openSqlSession.close();

    }


}
```

### 通过接口映射

> namespace:命名空间 -> 指定为接口
>
> id:唯一标识 -> 指定为接口方法名称

EmployeeMapper.xml

```xml
<mapper namespace="U1_helloWorld.dao.EmployeeMapper">
    <select id="getEmpById" resultType="U1_helloWorld.bean.Employee">
    select id,last_name as lastName,gender,email from tab1_employee where id = #{id}
  </select>
</mapper>
```

EmployeeMapper

```java
public interface EmployeeMapper {

    public Employee getEmpById(Integer id);
}
```

实现

```java
public SqlSessionFactory getSqlSessionFactory() throws IOException {
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    return new SqlSessionFactoryBuilder().build(inputStream);
}
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
```

### 总结

> 1. 接口式编程
>    原生:			Dao 		 ==== >	DaoImpl
>    mybatis:     Mapper	====>	xxMapper.xml
>
> 2. SqlSession代表和数据库的一次会话;用完必须关闭;
> 3. SqlSession和connection-样她都是非线程安全。每次使用都应该去获取新的对象。
> 4. mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象。
>    (将接口和xm1进行绑定)
>    EmployeeMapper empMapper = sqlSession. getMapper(EmployeeMapper .class)
> 5. 两个重要的配置文件:
>    mybati s的全局配置文件:包含数据库连接池信息，事务管理器信息等。. .系统运行环境信息
>    sq1映射文件:保存了每一个sq1语句的映射信息:
>                   将sq1抽取出来。

## 全局配置文件

#### properties

> mybatis可以使用properties来引入外部properties配置文件的内容;
> resource: 引入类路径下的资源
> url:引入网络路径或者磁盘路径下的资源

```xml
<properties resource="JdbcConfig.properties"/>

<environments default="development">
    <environment id="development">
        <transactionManager type="JDBC"/>
        <dataSource type="POOLED">
            <property name="driver" value="${jdbc.driver}"/>
            <property name="url" value="${jdbc.url}"/>
            <property name="username" value="${jdbc.username}"/>
            <property name="password" value="${jdbc.password}"/>
        </dataSource>
    </environment>
</environments>
```

#### setting

> setting包含很多重要的设置项
>         setting:用来设置每一个设置项
>             name：设置项名
>             value：设置项取值

```xml
<configuration>
	<settings>
    	<!-- 数据库为a_column时 自动 映射到javaBean中的aColumn  -->
    	<setting name="mapUnderscoreToCamelCase" value="true"/>
	</settings>
    ...
    ...
</configuration>
```

#### typeAliases

##### 单个

> typeAliases别名处理器 可以为我们的java类型起别名

> typeAlias 为某个java类型起别名
>                 type:指定要起别名的类型全类名,默认别名是小写employee
>                 alias:指定新的别名

mybatis-config.xml 全局配置文件

> 不配置alias属性自动为类名小写employee

```xml
<typeAliases>
    <typeAlias type="U1_helloWorld.bean.Employee" alias="employee"/>
</typeAliases>
```

EmployeeMapper.xml SQL语句映射文件

```xml
<select id="getEmpById" resultType="U1_helloWorld.bean.Employee">
  select * from tab1_employee where id = #{id}
</select>

<!-- 配置前后 -->

<select id="getEmpById" resultType="employee">
    select * from tab1_employee where id = #{id}
</select>
```

##### 批量

>  package :为某个包下的所有类批量起别名
>    	 name:指定包名（为当前包以及下面所有的后代包的每一个类都起一个默认别名(类名小写)

```xml
    <typeAliases>
        <package name="U1_helloWorld.bean"/>
    </typeAliases>
```

##### 注解

> 批量起别名的前提下

```java
@Alias("emp")
public class Employee {
}
```