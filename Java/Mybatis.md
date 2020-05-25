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

## 全局配置文件基础

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

#### environments

> environments环境们，mybatis可以配置多种环境
>
> 通过default指定使用环境切换环境

##### environment

> 配置一个具体的环境信息
>                 两个标签：
>                     id代表当前环境的唯一标识

######  transactionManager:

> 事务管理器(开发时主要用Spring)
>                     -> type:事务管理器类型(JDBC | MANAGED)
>                             自定义事务管理器，实现TransactionFactory.
>                             type指定全类型

######   dataSource

> 数据源 ;
>                     -> type: 数据源类型（UNPOOLED | POOLED | JNDI ）
>                         自定义数据源： 实现dataSourceFactory接口。type是全类名

##### 示例

```xml
<environments default="test">
    <environment id="test">
        <transactionManager type="JDBC"/>
        <dataSource type="POOLED">
            <property name="driver" value="${jdbc.driver}"/>
            <property name="url" value="${jdbc.url}"/>
            <property name="username" value="${jdbc.username}"/>
            <property name="password" value="${jdbc.password}"/>
        </dataSource>
    </environment>
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

#### dataBaseIdProvider

> 支持多数据厂商的： 
> type="DB_VENDOR" ：
> 作用就是得到数据库厂商标识(驱动getDataProductName())
> mybatis就能根据数据库厂商标识
> MySQL, Oracle, SQL Server

mybatis-config.xml

```xml
<databaseIdProvider type="DB_VENDOR">
    <!-- 为不同和数据库厂商起别名 -->
    <property name="MySQL" value="mysql"/>
    <property name="Oracle" value="oracle"/>
</databaseIdProvider>
```

EmployeeMapper.xml

```xml
  <select id="getEmpById" resultType="emp" databaseId="mysql">
  select * from tab1_employee where id = #{id}
</select>
```

#### mappers

> 配置多个mapper

##### mapper

> 注册一个sql映射

##### 通用配置

> resource : 引用类路径下的sql映射文件
>     		-> U1/EmployeeMapper.xml
> url： 引用网络路径或者磁盘路径下的sql映射文件
>    		-> file:///var//mappers/AuthorMapper.xml

mybatis-config.xml

```xml
<mapper resource="U1/EmployeeMapper.xml"/>
```

##### 使用接口

> 需要将 sq1映射文件，映射文件名必须和接口同名，并且放在与接口同一目录下;

mybatis-config.xml

```xml
<mapper class="U1_helloWorld.dao.EmployeeMapper"/>
```

##### 使用注解

> mybatis-config.xml

```xml
<mapper class="U1_helloWorld.dao.EmployeeMapperAnnotation"/>
```

```java
public interface EmployeeMapperAnnotation {

    @Select("select * from tab1_employee where id = #{id}")
    Employee getEmpById(Integer id);
}
```

##### 批量注册

>  mybatis-config.xml

```xml
<package name="U1_helloWorld.dao"/>
```

## 增删查改

>注意：
>
>1. session需要手动提交【openSession(true);可以自动提交】
>2. 返回值直接在接口中定义

在映射文件中配置

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="U2_Mapper.dao.EmployeeMapper">

    <select id="getEmpById" resultType="emp2" databaseId="mysql">
        select *
        from tab1_employee
        where id = #{id}
    </select>

    <!-- parameterType 可以省略 -->
    <insert id="addEmp" parameterType="emp2">
        insert into tab1_employee(last_name, gender, email)
        values (#{lastName}, #{gender}, #{email})
    </insert>

    <update id="updateEmp">
        update tab1_employee
        set last_name=#{lastName},
            email=#{email},
            gender=#{gender}
        where id=#{id}
    </update>

    <delete id="deleteEmpById">
        delete from tab1_employee where id=#{id}
    </delete>
</mapper>
```

接口

```java
public interface EmployeeMapper {

    Employee getEmpById(Integer id);

    void addEmp(Employee employee);

    boolean updateEmp(Employee employee);

    void deleteEmpById(Integer id);


}
```

方法

```java
public void testAdd() throws IOException {
    SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

    SqlSession openSession = sqlSessionFactory.openSession();


    try {
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

        Employee employee = new Employee(null, "ahhTouProMax", "男", "12223@qq.com");
        Employee employeeToUpdate = new Employee(14, "ahhTouProMaxPlus", "男", "12223@qq.com");

        mapper.addEmp(employee);

        mapper.deleteEmpById(1);

        boolean b = mapper.updateEmp(employeeToUpdate);

        System.out.println("更新员工"
                +employeeToUpdate.getId() + "更新 "+b + "了");
        // 手动提交
        openSession.commit();
    } finally {
        openSession.close();
        System.out.println("--END--");
    }
}
```

## insert得到自增主键的值

>  mysql自增主键，自增主键的获取，mybatis也是利用statement.getGeneratedKeys()
>
>  ​	useGeneratedKeys="true" : 使用自增主键获取主键值的策略
>  ​	**keyProperty**:指定对应的主键属性，也就是mybatis之后，把这个值封装javaBean哪个属性

```xml
<insert
        id="addEmp"
        parameterType="emp2"
        useGeneratedKeys="true"
        keyProperty="id"
>
    insert into tab1_employee(last_name, gender, email)
    values (#{lastName}, #{gender}, #{email})
</insert>
```

```java
            

	Employee employee = new Employee(null, 
                                 "ahhTouProMax", "男", "12223@qq.com");
	// insert
	mapper.addEmp(employee);
	// 可以得到id值
	System.out.println(employee.getId());
```

## MyBatis参数处理

#### 单个参数

> mybatis 不会做特殊处理，
>
> #{参数名}：取出参数值

#### 多个参数

mybatis会做特殊处理

​		多个参数会被封装成一个map

​		key: param1...paramN,或者参数索引也可以

​		value：传入的参数值

#{}就是从map中获取指定的key值

命名参数，明确指定封装参数时的map的key

#### 直接使用param

```xml
<select id="getEmpByIdAndLastName" resultType="emp2">
    select *
    from tab1_employee
    where id = #{param1} and last_name = #{param2}
</select>
```

#### 使用注解

###### 接口

```java
Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);
```

###### 映射文件

```xml
<select id="getEmpByIdAndLastName" resultType="emp2">
    select *
    from tab1_employee
    where id = #{id} and last_name = #{lastName}
</select>
```

#### POJO

如果多个参数正好是我们业务逻辑的数据模型，我们就可以直接传入pojo; 
#{属性名}:取出传入的pojo的属性值

#### MAP

接口

```xml
Employee getEmpByMap(Map<String, Object> map);
```

映射

```
<select id="getEmpByMap" resultType="emp2">
    select *
    from tab1_employee
    where id = #{id} and last_name = #{lastName}
</select>
```

测试

```java
Map<String, Object> map = new HashMap<String, Object>();
map.put("id", 16);
map.put("lastName","ahhTouProMax");

System.out.println( mapper.getEmpByMap(map));
```

#### TO

#### 思考

``` java

public Employee getEmp(@Param("id")Integer id,String lastName );
// #{id 或者 param1}
// #{ param2 }

public Emp1oyee getEmp(Integer id, @Param("e")Employee emp);
// #{param1}
// #{ param2.lastName 或者e.lastName }

public Employee getEmpById(List<Integer> ids);
// 取出第一个id的值: 
// #{list[0]}
```

如果是Collection (List、Set)类型或者是数组，也会特殊处理。也是把传入的list或者数组封装在map中。
key: Collection (collection), key(list), 数组(array)

#### ${} 和 #{}的区别

#{} :是以预编译的形式，将参数设置到sq1语句中; PreparedStatement;防止sql注入
${ } :取出的值直接拼装在sq1语句中;会有安全问题;
大多情况下，我们去参数的值都应该去使用#{ };
原生jdbc不支持占位符的地方我们就可以使用${ }进行取值
比如分表;按照年份分表拆分
select * from ${year}_ salary where XXX;
select * from tbl_ employee order by ${f_ name} ${}|

> #{}更安全，但不支持字符串拼接

> ${}支持字符串拼接，但是不安全

#### #{}更丰富的用法

规定参数的一些规则:
javaType、jdbcType、mode (存储过程)、numericScale、
resultMap、typeHandler、 jdbcTypeName、 expression (未来准备支持的功能) ;

> 解决Oracle不支持null的方式【Oracle不支持默认的OTHER】
>
> #{name, jdbcType=NULL}
>
> 或者在全局配置文件中配置
>
> ```xml
> <configuration>
> 	...
> 	<settings>
> 	...
>     ...
>    		 <setting name="jdbcTypeForNull" value="NULL"/>
> 	</settings>
> </configuration>
> ```

#### 返回值为List

接口

```java
List<Employee> getEmpByLastNameLike(String lastName);
```

映射

>  resultType 如果返回的是一个集合，要写集合中元素 的 类型 

```xml
<select id="getEmpByLastNameLike" resultType="emp2">
    select * from tab1_employee where last_name like concat('%',#{lastName},'%')
</select>
```

测试

```java
List<Employee> a = mapper.getEmpByLastNameLike("%a%");

for (Employee employee : a){
    System.out.println(employee);
}
```

#### 返回值为Map

##### 单条，key为列名称，value为值

接口

```java
// 返回一条记录的map，key是列名，值就是对应的值
Map<String, Object> getEmpByIdReturnMap(Integer id);
```

映射

> 封装为map

```xml
<select id="getEmpByIdReturnMap" resultType="map">
    select *
    from tab1_employee
    where id = #{id}
</select>
```

测试

```java
Map<String, Object> map = mapper.getEmpByIdReturnMap(13);
System.out.println(map);
```



##### 多条，key为单项记录的主键， value为javaBean对象

接口

> 注解@MapKey 告诉mybatis封装这个map的时候使用哪个属性作为map的key

```java
// 多条记录封装一个map: Map< Integer , Employee> :键匙这条记录的主键，值是记录封装后的JavaBean
@MapKey("id") //告诉mybatis封装这个map的时候使用哪个属性作为map的key
Map<Integer, Employee> getEmpByLastNameLikeReturnMap(String lastName);
```

映射

>

```xml
<select id="getEmpByLastNameLikeReturnMap" resultType="emp2">
    select *
    from tab1_employee
    where last_name like concat('%', #{lastName}, '%')
</select>
```

测试

```java
Map<Integer, Employee> a = mapper.getEmpByLastNameLikeReturnMap("a");
System.out.println(a);
```

##### ReslutMap自定义返回类型

> 自定义结果集映射规则，和resultType只能二选一

ReslutMap

> type:自定义规则的Java类型
>  id：唯一id方便引用
>
> <id>主键 ：id定义主键会底层有优化
>
> <result>非主键 ： column 指定那一列，property 对应javaBean的属性



```xml
    <resultMap type="U2_Mapper.bean.Employee" id="MyEmp">
        <!--指定主键列的封装规则
        id定义主键会底层有优化
            column 指定那一列
            property 对应javaBean的属性
         -->
        <id column="id" property="id"/>
        <!-- 定义普通列封装规则 -->
        <result column="last_name" property="lastName"/>
        <!-- 其他不指定的列会自动封装，我们只有写resultMap就把全部映射规则都写上 -->
        <result column="email" property="email"/>
        <result column="gender" property="gender"/>
    </resultMap>
```