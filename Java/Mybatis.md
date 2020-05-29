#### MyBatis

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

#### ReslutMap自定义返回类型

> 自定义结果集映射规则，和resultType只能二选一

ReslutMap

> type:自定义规则的Java类型
>  id：唯一id方便引用
>
> <id>主键 ：id定义主键会底层有优化
>
> <result>非主键 ： column 指定那一列，property 对应javaBean的属性

##### 简单类型

类

```java
private Integer id;

private String lastName;

private String gender;

private String email;
```

映射

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

    <select id="getEmpById" resultMap="MyEmp">
        select *
        from tab1_employee
        where id = #{id}
    </select>
```

##### 复杂类型

Employee类

```java
private Integer id;

private String lastName;

private String gender;

private String email;

private Department dept;
```

Department类

```java
private Integer id;
private String departmentName;
```

映射

```xml
<resultMap id="MyDifEmp" type="U2_Mapper.bean.Employee">
    <id column="id" property="id"/>
    <result column="last_name" property="lastName" />
    <result column="gender" property="gender" />
    <result column="did" property="dept.id" />
    <result column="dept_name" property="dept.departmentName" />
</resultMap>
<select id="getEmpAndDept" resultMap="MyDifEmp">
    SELECT e.id          AS id,
           e.last_name   AS Last_name,
           e.`gender`    AS gender,
           e.`d_id`      AS d_id,
           d.`id`        AS did,
           d.`dept_name` AS dept_name
    FROM tab1_employee AS e,
         tb1_dept d
    WHERE e.d_id = d.id
      AND e.id = 13
</select>
```

##### 复杂类型使用association

其他同复杂类型，映射如下

>association 可以指定联合的javaBean对象

> property = "dept" 指定哪个属性是联合的对象
> javaType ：指定这个属性对象的类型【不能省略】

```xml
<resultMap id="MyDifEmpPro" type="U2_Mapper.bean.Employee">
    <id column="id" property="id"/>
    <result column="last_name" property="lastName" />
    <result column="gender" property="gender" />

    <association property="dept" javaType="U2_Mapper.bean.Department">
        <id column="did" property="id" />
        <result column="dept_name" property="departmentName" />
    </association>
</resultMap>
```

##### 使用association分布查询

> 新建一个映射和接口

接口 DepartmentMapper

```java
public interface DepartmentMapper {
    Department getDeptByID(Integer id);
}
```

映射 DepartmentMapper.xml

```xml
<mapper namespace="U2_Mapper.dao.DepartmentMapper">
    <select id="getDeptByID" resultType="U2_Mapper.bean.Department">
        select id, dept_name as departmentName
        from tb1_dept
        where id=#{id}
    </select>
</mapper>
```

> 使用 association调用新建的接口和映射
>
> 使用association进行分布查询
>         1.先按照员工id查询员工信息
>         2.根据查询鱼啊弄信息中的d_id值去部门表查出部门信息
>         3.部门设置到员工中
>
> association定义关联对象的封装规则
>             select：表名当前属性是调用select指定的方法查出的结果
>             column：指定哪一列的值传给这个对象
>
> ​            流程：使用select指定的方法（传入column）查出对象并封装给property指定的属性

```xml
<resultMap id="MyEmpByStep" type="U2_Mapper.bean.Employee">
    <id column="id" property="id"/>
    <result column="last_name" property="lastName" />
    <result column="email" property="email" />
    <result column="gender" property="gender" />
    <association
            property="dept"
            select="U2_Mapper.dao.DepartmentMapper.getDeptByID"
            column="d_id"
    />
</resultMap>
    
<select id="getEmpByIdStep" resultMap="MyEmpByStep">
    select * from tab1_employee where id=#{id}
</select>
```

#####  association的懒加载特性

可以使用延迟加载
            Employee => Dept:
                我们每次查询Employee对象的时候，都将一起查询出来。
                部门信息在我们使用的时候再去查询;
                分段查询的基础之上加上两个配置:

全局设置

```xml
<settings>
    ...
    ...
    <!-- 懒加载 -->
	<setting name="lazyLoadingEnabled" value="true"/>
    <!-- 轻度懒加载 -->
	<setting name="aggressiveLazyLoading" value="false"/>
</settings>	
```

##### collection定义关联集合封装规则

> 目的：查询到部门信息，并包含所有该部门的员工

department类

```java
private Integer id;
private String departmentName;
private List<Employee> empS;
```

接口

```java
Department getDeptByIdPlus(Integer id);
```

映射

```xml
<!-- collection嵌套结果集的方式 定义关联的集合类型的元素封装规则封装对象  -->
<resultMap id="MyDept" type="U2_Mapper.bean.Department">
    <id column="did" property="id"/>
    <result column="dept_name" property="departmentName"/>
    <!--
        collection定义关联集合类型的属性封装规则
        ofType:指定集合里元素的类型
    -->
    <collection property="empS" ofType="U2_Mapper.bean.Employee">
        <!-- 定义这个集合中 元素中的封装规则-->
        <id column="eid" property="id"/>
        <result column="last_name" property="lastName"/>
        <result column="email" property="email"/>
        <result column="gender" property="gender"/>
    </collection>
</resultMap>

<select id="getDeptByIdPlus" resultMap="MyDept">
    select d.id        did,
           d.dept_name dept_name,
           e.id        eid,
           e.last_name last_name,
           e.email     email,
           e.gender    gender
    from tb1_dept d
             left join tab1_employee e on d.id = e.d_id
    where d.id = #{id}
</select>
```

##### 使用collection分布查询

###### 先写一个按工号查询的映射

Employee的接口

```java
List<Employee> getEmpSDeptId(Integer deptId);
```

Employee映射

```xml
<!-- 按照部门id查询所有员工 -->
<select id="getEmpSDeptId" resultType="U2_Mapper.bean.Employee">
    select * from tab1_employee where d_id = #{id}
</select>
```

###### 在Department中继续编写

DepartmentMapper

```java
Department getDeptByIdStep(Integer id);
```

DepartmentMapper.xml

> column=“” 来传递参数

```java
<!-- 分步查询 -->
<resultMap id="MyDeptStep" type="U2_Mapper.bean.Department">
    <id column="id" property="id"/>
    <id column="dept_name" property="departmentName"/>
    <collection
            property="empS"
            select="U2_Mapper.dao.EmployeeMapperPlus.getEmpSDeptId"
            column="id"
    >
    </collection>

</resultMap>
<select id="getDeptByIdStep" resultMap="MyDeptStep">
    select id, dept_name as departmentName
    from tb1_dept
    where id = #{id}
</select>
```

##### 补充：分布多列值的传递&fetchType

###### 分布多列值的传递

>  将多列的值封装map传递;
>  column="{key1=column1, key2=column2}"

举例，如上面

```xml
 column="id"
```

可以改为 id为值，对应的参数为键

```xml
column="{deptId=id}"
```

##### fetchType

> 在 association 和  collection标签中添加 可以改变是否为延迟加载，而不用修改全局

| fetchType属性 | 作用 |
| ------------- | ---- |
| lazy          | 延迟 |
| eager         | 立即 |

#### discriminator 鉴别器

>  鉴别器：mybatis可以试用discriminator判断某列的值，然后根据某列的值，改变封装行为

##### 要求

   封装Employee：
            如果查出的是女生 就把部门信息查询出来，否者不查询
            如果是男生，把last_name这一列的值赋值给email

##### 定义resultMap 和 方法映射

```xml
<select id="getEmpById" resultMap="MyEmpDis">
    select *
    from tab1_employee
    where id = #{id}
</select>

<resultMap id="MyEmpDis" type="U2_Mapper.bean.Employee">
    <id column="id" property="id"/>
    <result column="last_name" property="lastName" />
    <result column="email" property="email" />
    <result column="gender" property="gender" />
    <!-- column指定判断的列
        javaType 列值对应的java类型
     -->
    <discriminator javaType="string" column="gender">
        <!-- 女生 resultType:指定封装的结果类型,不能缺少 -->
        <case value="0" resultType="U2_Mapper.bean.Employee">
            <association
                    property="dept"
                    select="U2_Mapper.dao.DepartmentMapper.getDeptByID"
                    column="d_id"
            />
        </case>
        <!--如果是男生，把last_name这一列的值赋值给email -->
        <case value="1" resultType="U2_Mapper.bean.Employee">
            <id column="id" property="id"/>
            <result column="last_name" property="lastName" />
            <result column="last_name" property="email" />
            <result column="gender" property="gender" />
        </case>
    </discriminator>
</resultMap>
```

## 动态SQL

### if

> 判断表达式 （OGNL）会进行字符串和数字的转换判断

> 会出现字符串拼接问题 可以使用 where 1=1解决

```xml
<select id="getEmpSByConditionIf" resultType="emp3">
    select * from tab1_employee
    where 1=1
    <if test="id!=null">
        and id=#{id}
    </if>
    <if test="lastName!=null and lastName!=&quot;&quot;">
        and last_name like concat('%',#{lastName},'%')
    </if>
    <if test="email!=null &amp;&amp; email!=&quot;&quot;">
        and email=#{email}
    </if>
    <if test="gender==0 or gender==1">
        and gender=#{gender}
    </if>
</select>
```

### where封装查询条件

> 解决字符串拼接问题，比1=1更为合理，但是and必须放到语句前

```xml
<select id="getEmpSByConditionIf" resultType="emp3">
    select * from tab1_employee
    <where>
        <if test="id!=null">
            and id=#{id}
        </if>
        <if test="lastName!=null and lastName!=&quot;&quot;">
            and last_name like concat('%',#{lastName},'%')
        </if>
        <if test="email!=null &amp;&amp; email!=&quot;&quot;">
            and email=#{email}
        </if>
        <if test="gender==0 or gender==1">
            and gender=#{gender}
        </if>
    </where>
</select>
```

### trim

> 使用并不多

| 属性             | 名称     |                                       |
| ---------------- | -------- | ------------------------------------- |
| prefix           | 前缀     | prefix 给拼串后的整个字符型加一个前缀 |
| prefixOverrides  | 前缀覆盖 | 去掉整个字符串前面多余的字符          |
| shuffix          | 后缀     | 给拼串后的整个字符串加一个后缀        |
| shuffixOverrides | 后缀覆盖 | 去掉整个字符串后面对于的字符          |

> 查询条件只有email不为空

```xml
<trim prefix="where" suffixOverrides="and">
    <if test="id!=null">
        and id=#{id}
    </if>
    <if test="lastName!=null and lastName!=&quot;&quot;">
        and last_name like concat('%',#{lastName},'%')
    </if>
    <if test="email!=null &amp;&amp; email!=&quot;&quot;">
        email=#{email} and
    </if>
    <!--ognl会进行字符串和数字的转换判断 "0"==0 -->
    <if test="gender==0 or gender==1">
        and gender=#{gender}
    </if>
</trim>
```

### choose

> 某个choose匹配到后后面的都不执行，而都没有匹配到时，执行otherwise

```xml
<select id="getEmpSByConditionChoose" resultType="emp3">
    select * from tab1_employee
    <where>
        <!-- 如果带了id就用id查，如果带了lastName就用lastName查 -->
        <choose>
            <when test="id!=null">
                id=#{id}
            </when>
            <when test="lastName!=null">
                last_name like concat('%',#{lastName},'%')
            </when>
            <when test="email!=null">
                email = #{email}
            </when>
            <otherwise>
                gender = 0
            </otherwise>
        </choose>
    </where>
</select>
```

### set封装修改条件

#### 常规

> 必须三个值都具备

```xml
<update id="updateEmp2">
    update tab1_employee
    set last_name=#{lastName},
        email=#{email},
        gender=#{gender}
    where id = #{id}
</update>
```

#### set标签

> 有哪个值，更新哪个，但是set标签内不能有 ' , '

```xml
<update id="updateEmpSet">
    update tab1_employee
    set
    <if test="lastName!=null">
        last_name=#{lastName}
    </if>
    <if test="email!=null">
        email=#{email}
    </if>
    <if test="gender!=null">
        gender=#{gender}
    </if>
    where id=#{id}
</update>
```

#### trim优化

> 可以去除尾的，号

```xml
<update id="updateEmpTrimSet">
    update tab1_employee
    <trim prefix="set" suffixOverrides=",">
        <if test="lastName!=null">
            last_name=#{lastName},
        </if>
        <if test="email!=null">
            email=#{email},
        </if>
        <if test="gender!=null">
            gender=#{gender},
        </if>
    </trim>
    where id=#{id}
</update>
```

### foreach

#### 通过遍历添加条件

> 使用 list 、map的时候collection的值要为list、map

##### 属性

| 属性       |                                                              |
| ---------- | ------------------------------------------------------------ |
| collection | 定要遍历的集合     list类型的参数会特殊处理封装到map中，map的key叫list |
| item       | 将当前遍历出的元素赋值给当前遍历出的元素                     |
| separator  | 每个元素之间的分隔符                                         |
| open       | 遍历出所有结果拼接一个 开始的字符                            |
| close      | 遍历出所有结果拼接一个 开始的字符                            |
| index      | 索引。遍历list的时候是索引，遍历map的时候indnx表示的就是map的key，item就是map的值 |

##### 使用

```xml
<select id="getEmpSByConditionForeach" resultType="emp3">
    select * from tab1_employee where id in(
        <foreach
            collection="listPro"
            item="item_id"
            separator=","
    >
        #{item_id}
    </foreach>
    )
</select>
```

sql语句

```sql
 select * from tab1_employee where id in(1, 2, 3)
```

使用open和close可以优化代码

```xml
<select id="getEmpSByConditionForeach" resultType="emp3">
    select * from tab1_employee where id in
    <foreach
            collection="listPro"
            item="item_id"
            separator=","
            open="("
            close=")"
    >
        #{item_id}
    </foreach>
</select>
```

#### 批量插入

方式1 推荐

```xml
<insert id="addEmpS">
    insert into tab1_employee(last_name, gender, email, d_id)
    values
    <foreach collection="empS" item="emp" separator=",">
        (#{emp.lastName},#{emp.gender},#{emp.email},#{emp.dept.id})
    </foreach>

</insert>
```

方式2

```xml
<insert id="addEmpS">
    <foreach collection="empS" item="emp" separator=";">
        insert into tab1_employee(last_name, gender, email, d_id)
        values (#{emp.lastName},#{emp.gender},#{emp.email},#{emp.dept.id})
    </foreach>
</insert>
```

#### 内置参数

> _parameter: 代表整个参数
>        单个参数: . parameter就是这个参数
>        多个参数:参数会被封装为一个map;_ _parameter就是代表这 个map

场景：用来判断也没有参数

```xml
select * from tab1_employee
<if test="_parameter!=null" >
    where id=#{id}
</if>
```


> _databaseId :如果配置了databaseIdProvider标签。
>         _databaseId且代末当前数据库的别文Oracle
场景: 用来判断是哪个数据库

```xml
<if test="_databaseId=='mysql'" >
    select * from tab1_employee
</if>
<if test="_databaseId=='oracle'">
    select * from tb1_dept
</if>
```

### bind

> 可以将OGNL表达式的值绑定到一一个变量中，方便后来引用这个变量的值 

```xml
    <select id="getEmpSTestInnerParam" resultType="emp3">
        <bind name="_lastName" value="'%'+lastName+'%'"/>
        select * from tab1_employee where last_name like #{_lastName}
    </select>
```

### Sql和include抽取重复语句

>  抽取可重用的sql片段。方便后面引用
>            1. SQL抽取：经常要查询的列名，或者插入用的列名出来方便引用
>         2. include来引用已经抽取了的sql语句
>            3. include还可以自定义写property，sql标签内部就能使用自定义属性
>   取值的正确方式${prop}，不用#{}

```xml
    <select id="getEmpSTestInnerParam" resultType="emp3">
        <if test="_databaseId=='mysql'">
            <!-- 引入外部sql -->
            <include refid="insertColumn" >
                <property name="testProp" value="'a'"/>
            </include>
        </if>
    </select>
    <sql id="insertColumn">
        select * from tab1_employee where last_name like concat('%',${testProp},'%')
    </sql>
```

## 缓存

### 一级缓存

```
* 一级缓存：（本地缓存）：sqlSession级别的缓存。一 级缓存是一 直开启的
*      与数据库同一次会话期间查询到的数据会放在本地缓存中。
*      以后如果需要获取相同的数据，直接从缓存中拿，没必要再去查询数据库;
*
*      一级缓存失效情况(没有使用到当前一-级缓存的情况，效果就是，还需要再向数据库发出查询)
*              1、sqlSession不同
*              2、sqlSession相同，查询条件不同
*              3、sqlSession相同，两次查询之间执行了增删改操作(这次增删改可能对当前数据有影响)
*              4、sqlSession相同,手动清空缓存 openSession.clearCache();
```

### 二级缓存

> 一级缓存的session关闭后,才会缓存到二级缓存中

#### 描述

> 基于namespace级别的缓存,一个namespace对应一个二级缓存

###### 工作机制

1. 一个会话，查询一条数据，这个数据就会被放在当前会话的一级缓存中;
2. 如果会话关闭; - -级缓存中的数据会被保存到二级缓存中;新的会话查询信息，就可以参照二级缓存
3. sqlSession  ===    EmployeeMapper==> Employee
                    				DepartmentMapper=== >Department
      				 				不同namespace查出的数据会放在自己对应的缓存中(map)

######   效果

数据会从二级缓存中获取I
查出的数据都会被默认先放在一级缓存中 。
只有会话提交或者关闭以后，- 级缓存中的数据才会转移到二级缓存中

#### 使用

1. 开启全局二级缓存：<setting name="cacheEnabled" value="true"/>

   > 值为false时，一级缓存无法关闭

   ```xml
   <settings>
   	...
       ...
       <setting name="cacheEnabled" value="true"/>
   </settings>
   ```

   

2. 去mapper.xml中配置使用二级缓存    <cache></cache>

   ```xml
   <mapper namespace="U4_Cache.dao.EmployeeMapper">
       <cache eviction="FIFO" flushInterval="60000" readOnly="true" size="1024" >
       </cache>
       ...
       ...
   </mapper>
   ```

   ​	

   | 属性名称                  | 简述                                                         |
   | ------------------------- | ------------------------------------------------------------ |
   | eviction缓存的回收策略    | ● LRU -最近最少使用的:移除最长时间不被使用的对象。 <br />● FIFO -先进先出:按对象进入缓存的顺序来移除它们。<br />● SOFT -软引用:移除基于垃圾回收器状态和软引用规则的对象。<br />● WEAK -弱引用:更积极地移除基于垃圾收集器状态和弱引用规则的对象。 <br />● 默认的是LRU。 |
   | flushInterval缓存刷新间隔 | 缓存多长时间清空一次， 默认不清空，设置-个毫秒值             |
   | read0nly是否只读          | true:只读; <br />mybatis认为所有 从缓存中获取数据的操作都是只读操作，不会修改数据。         <br />mybatis为了加快获取速度，直接就会将数据在缓存中的引用交给用户。不安全，速度快 <br />false:非只读: mybatis觉得获取的数据可能会被修改。         <br />mybatis会利用序列化&反序列的技术克隆一份新的数据给你。 安全，速度慢 |
   | size                      | 指定自定义缓存的大小                                         |
   | type                      | 指定自定义缓存的全类名                                       |

   

3. 我们POJO需要事先序列化接口

   ```java
   public class Department  implements Serializable {
       ...
       ...
   }
   ```



### 和缓存有关的设置/属性

###### 在全局设置中关闭缓存

> 仅关闭二级缓存

```xml
<settings>
	...
    ...
    <setting name="cacheEnabled" value="false"/>
</settings>
```

###### 在mapper中关闭缓存

> 仅关闭二级缓存

```xml
<select id="getEmpByLastNameLike" resultType="emp4" useCache="false">
    select * from tab1_employee
</select>
```

###### mapper中每个操作清空缓存

> 查询操作默认是false，其他标签是true，一级二级缓存都会被清空

```xml
<select id="getEmpByMap" resultType="emp4" flushCache="true">
    select * from tab1_employee
</select>
```

###### 会话中清除

> 仅清空一级缓存

```java
sqlSession.claerCahe();
```

###### 本地缓存作用域

```xml
<setting name="localCacheScope" value="STATEMENT"/>
```

### 缓存顺序

> 先找 二级 再找 一级

### 引用缓存

> 在其他mapper文件中使用<cache-ref namespace=""/>可以和该命名空间使用同一个缓存

EmployeeMapper.xml

```xml
<mapper namespace="U4_Cache.dao.EmployeeMapper">
    <cache type="org.mybatis.caches.ehcache.EhcacheCache"/>
</mapper>
```

DepartmentMapper.xml

```xml
<mapper namespace="U4_Cache.dao.DepartmentMapper">
    <!-- 引用缓存 -->
    <cache-ref namespace="U4_Cache.dao.EmployeeMapper"/>
</mapper>
```

### 整合ehcache

###### 依赖

```xml
<dependency>
    <groupId>net.sf.ehcache</groupId>
    <artifactId>ehcache-core</artifactId>
    <version>2.6.11</version>
</dependency>

<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.25</version>
</dependency>
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-log4j12</artifactId>
    <version>1.7.25</version>
</dependency>

<dependency>
    <groupId>org.mybatis.caches</groupId>
    <artifactId>mybatis-ehcache</artifactId>
    <version>1.2.1</version>
</dependency>
```