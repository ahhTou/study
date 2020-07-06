# 一、Spirng Boot入门

## 1、 Spring Boot简介

> 简化Spring应用开发的一一个框架;
> 整个Spring技术栈的一个大整合;
> J2EE开发的一站式解决方案;

## 2、微服务

微服务：架构风格

一个应用应该是一组小型服务；可以通过HTTP的方式进行互通

每一个功能元素最终都是一个可独立 替换和独立升级的软件单元

## 3、依赖

```xml
<!-- spring boot  -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.5.9.RELEASE</version>
</parent>
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

## 4、Hello World

一个功能:
浏览器发送hello请求,服务器接受请求并处理,响应Hello World字符串;

### 1、创建一个maven工程；（jar）

### 2、导入依赖spring boot

### 3、编写一个主程序

```java
/**
 * @SpringBootApplication 来标注一个主程序类，说明这是一个Spring Boot应用
 */
@SpringBootApplication
public class HelloWorldMainApp {


    public static void main(String[] args) {

        // Spring应用启动起来
        SpringApplication.run(HelloWorldMainApp.class, args);

    }
}
```

### 4、编写Controller、和Service

```java
@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello World!!!";
    }
}
```

### 5、运行主程序测试

### 6、简化部署

> 使用 java -jar

```xml
<!-- 这个插件， 可以将应用打包成一个可以执行的jar包 -->
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

## 5、Hello World的探究

### 1、 pom文件

#### 1、父项目

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.5.9.RELEASE</version>
</parent>

该项目依赖的父项目是
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-dependencies</artifactId>
		<version>1.5.9.RELEASE</version>
		<relativePath>../../spring-boot-dependencies</relativePath>
	</parent>
他真正管理spring boot应用里面的所有以来版本
```

Spring Boot的版本仲裁中心

以后我们导入的依赖默认是不需要写版本的；

#### 2、导入的依赖

​	**spring-boot-starter** : spring-boot场景启动器;帮我们导入了web模块正常运行所依赖的组件;

Spring Boot将所有的功能场景都抽取出来,做成一个个的starters (启动器) , 只需要在项目里面引入这些
starter相关场景的所有依赖都会导入进来。要用什么功能就导入什么场景的启动器

### 2、主程序类，主入口类

```
/**
 * @SpringBootApplication 来标注一个主程序类，说明这是一个Spring Boot应用
 */
@SpringBootApplication
public class HelloWorldMainApp {


    public static void main(String[] args) {

        // Spring应用启动起来
        SpringApplication.run(HelloWorldMainApp.class, args);

    }
}
```

#### **@SpringBootApplication**

SpringBoot的配置类

标注在某个类上说明这个类是SpringBoot的主配置类，

##### **@Configuration**

配置类来标注这个注解；

配置类----配置文件;配置类也是容器中的一个组件; @Component



#### **@EnableAutoConfiguration**

开启自动配置功能

以前我们需要配置的东西, Spring Boot帮我们自动配置; @EnableAutoConfiguration告诉SpringBoot开启自动配置功能;这样自动配置才能生效;					

##### **@AutoConfigurationPackage** : 自动配置包

@Import(AutoConfigurationPackages.Registrar.class)

Spring的底层注解@Import ,给容器中导入一个组件;导入的组件由
AutoConfigurationPackages.Registrar.class ;

`将主配置类( @SpringBootApplication标注的类)的所在包及下面所有子包里面的所有组件扫描到Spring容器;`
@lmport(EnableAutoConfigurationlmportSelector.lass) ;
给容器中导入组件?
EnableAutoConfigurationlmportSelector :导入哪些组件的选择器;
将所有需要导入的组件以全类名的方式返回;这些组件就会被添加到容器中;
会给容器中导入非常多的自动配置类( xxAutoConfiguration) ; 就是给容器中导入这个场景需要的所有主键，并配置好这些组件

有了自动配置类,免去了我们手动编写配置注入功能组件等的工作;

SpringFactoriesLoader.loadFactoryNames(EnableAutoConfiguration.lass,classLoader);

Spring Boot在启动的时候从类路径下的META-INF/spring.factories中获取EnableAutoConfiguration指定的值，将这些值作为自动配置类导入到容器中,自动配置类就生效,帮我们进行自动配置工作;以前我们需要自己配置的东西，自动配置类都帮我们;

​									

**J2EE的整体整合解决方案和自动配置都在spring-boot-autoconfigure-1.5.9.RELEASE.jar ;**

## 6、使用Spring Initializer快速创建SpringBoot项目

IDE都支持使用Spring的项目创建向导快速创建一个Spring Boot项目;

选择我们需要的模块;向导会联网创建Spring Boot项目;

默认生成的Spring Boot项目;

- 主程序已经生成好了，我们只需要我们自己的逻辑

- resources文件夹中的目录结构

  - static 保存所有的静态资源

  - templates 保存所有的模板页面( Spring Boot默认jar包使用嵌入式的Tomcat ,默认不支持JSP页面)

  - application.properties ：SpringBoot应用的默认配置文件

    ```properties 
    server.port=8081
    ```

# 二、配置文件

## 1、配置文件

SpringBoot使用一个全局的配置文件

- application.properties
- application.yml



配置文件的作用：修改SpringBoot的自动配置的默认值；SpirngBoot底层都给我们自动配置好了



YAML（YAML Ain't Markup Language）

YAML A Markup Language 是一个标记语言

YAML isn't Markup Language 不是一个标记语言



标记语言:
以前的配置文件;大多都使用的是xxxx.xml文件;
YAML :以数据为中心,比json、xml等更适合做配置文件

YAML: 配置实例

```yml
server:
  port: 8081
```



## 2、YAML语法

### 1、基本语法

k:(空格)v :表示- -对键值对(空格必须有) ;

以**空格**的缩进来控制层级关系;只要是左对齐的一-列数据,都是同一个层级的

```yml
server:
  port: 8081
```

属性和值也是大小写敏感



### 2、值的写法

#### 字面量

> 普通的值（数值，字符串，布尔）

k: v :字面直接来写;
	字符串默认不用加上单引号或者双引号;
	

​	“ ” :双引号;不会转义字符串里面的特殊字符;特殊字符会作为本身想表示的意思

​	name: "zhangsan \n lisi" :输出; zhangsan 换行 lisi



​	‘ ‘  :单引号;会转义特殊字符，特殊字符最终只是一 个普通的字符串数据
​	name: 'zhangsan In lis' :输出; zhangsan \n lisi

#### 对象、Map（属性和值）（键值对）：

k: v在下一行来写对象的属性和值的关系;注意缩进
对象还是k: v的方式

```yml
friends:
  lastName: zhangShan
  age: 20
```

行内写法

```yml
frients: {lastName: zhangShan,age: 18}
```

#### 数组（List、Set）

用-值表示数组中的一个元素

```yml
pets:
  - cat
  - dog
  - pig
```

行内写法

```yml
pets: [cat, dog, pig]
```

### 3、配置文件值注入

```yml
person:
  lastName: ahhTou
  age: 18
  boss: false
  birth: 2017/12/12
  maps: {k1: v1, k2: 12}
  lists:
    - ahhTou2
    - ahhTou3
  dog:
    name: 狗
    age: 12
```

javaBean

```java
/**
 * 将配置文件中的配置的每一个属性的值，映射到这个组件中
 * @ConfigurationProperties: 告诉SpringBoot将本类中所有的属性和配置文件相关的配置进行绑定
 */

@Component
@ConfigurationProperties(prefix = "person")
public class Person {

    private String lastName;
    private Integer age;
    private Boolean boss;
    private Date birth;

    private Map<String, Object> maps;
    private List<Object> lists;
    private Dog dog;

}
```

我们可以导入配置文件处理器，以后编写配置就有提示了

```xml
    <!--  导入配置文件处理器，配置文件进行绑定就会有提示 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-configuration-processor</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

#### 1、properties的写法

```properties
person.last-name=ahhTouProperties
person.age=18
person.birth=2017/12/15
person.boss=false
person.maps.k1=v1
person.maps..k2=14
person.lists=a,b,c
person.dog.name=dog
person.dog.age=12
```

#### 2、@Value获取值 和 @ConfigurationProperties获取值比较

|                | @ConfigurationProperties | @Value     |
| -------------- | ------------------------ | ---------- |
| 功能           | 批量注入配置文件中的属性 | 一个个指定 |
| 松散绑定       | 支持                     | 不支持     |
| SpEL           | 不支持                   | 支持       |
| JSR303数据校验 | 支持                     | 不支持     |
| 复杂类型封装   | 支持                     | 不支持     |

配置文件yml还是properties他们都能获取到值

如果说，我们只是再某个业务逻辑中获取需要获取一下配置文件中的某个项，使用@Value

如果说，我们专门编写了一个JavaBean来和配置文件进行映射，我们直接使用ConfigurationProperties

```java
@Component
//@ConfigurationProperties(prefix = "person")
public class Person {

    /**
     * <bean class="Person">
     *      <property name="lastName" value="?"></property>
     * </bean>
     */

    @Value("${person.last-name}")
    private String lastName;
    @Value("#{11*2}")
    private Integer age;
    @Value("true")
    private Boolean boss;
    private Date birth;

    private Map<String, Object> maps;
    private List<Object> lists;
    private Dog dog;
```

#### 3、配置文件校验

#### 4、@PropertySource@ImportResource

@PropertySource： 加载指定的配置文件

```java
@Component
@PropertySource(value = {"classpath:person.properties"})
@ConfigurationProperties(prefix = "person")
public class Person {
    private String lastName;
    private Integer age;
    private Boolean boss;
    private Date birth;

    private Map<String, Object> maps;
    private List<Object> lists;
```

**@ImportResource**： 导入Spring的配置文件，让配置里的内容生效

Spring Boot里面没有Spring的配置文件,我们自己编写的配置文件,也不能自动识别;
想让Spring的配置文件生效,加载进来; **@ImportResource**标注在-个配置类上

```java
@ImportResource(locations = {"classpath:beans.xml"})
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```



不编写Spring的配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="helloService" class="com.ahhtou.config.service.HelloService"/>

</beans>
```

SpringBoot推荐给容器中添加组件的方式;推荐使用全注解的方式

1、配置类=====Spring配置文件

2、使用@Bean给容器添加组件

```java
@Configuration
public class MyAppConfig {

    // 将方法的返回值添加到容器中;容器中这个组件默认的id就是方法名
    @Bean
    public HelloService helloService() {
        System.out.println("添加了配置类");
        return new HelloService();
    }
}
```

### 4、配置文件占位符

#### 1、随机数

```}java
${random.value}
${random.int}
${random.long}
${random.int(10)}
${random.int[1024,65536]}
```

#### 2、占位符获取之前配置的值,如果没有可以是用:指定默认值

```properties
person.last-name=ahhTou${random.uuid}
person.age=18
person.birth=2017/12/15
person.boss=false
person.maps.k1=v1
person.maps..k2=14
person.lists=a,b,c
# 是hello_dog
person.dog.name=${person.hello:hello}_dog
person.dog.age=12
```

### 5、Profile

#### 1、多Profile文件

我们在主配置文件编写的时候,文件名可以是application-{profile).properties/yml

默认使用application.properties的配置

#### 2、yml支持多文档块文件

```
server:
  port: 8081
spring:
  profiles:
    active: prod
---
server:
  port: 8083
spring:
  profiles: dev
---
server:
  port: 8084
spring:
  profiles: prod
```

#### 3、激活指定profile

1、在配置文件中指定--spring.profiles.active=dev

2、命令行：

​		java -jar 03-config-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev

​		可以直接在测试的时候,配置传入命令行参数

3、虚拟机参数

​	-Dspring.profiles.active=dev

### 6、配置文件加载位置

springboot启动会扫描以下位置的application.properties或者application.ymI文件作为Spring boot的默认配置
文件
-file../config/
-file:./
-classpath:/config/
-classpath:/
优先级由高到底,高优先级的配置会覆盖低优先级的配置;
SpringBoot会从这四个位置全部加载主配置文件;**互补配置**;



我们还可以通过spring.config.location来改变默认的配置文件位置

**项目打包好以后，我们可以使用命令行参数的形式，启动项目的时候来指定配置文件的新位置;指定配置文件和默认加载的这些配置文件共同起作用形成互补配置; ** 

### 7、外部配置加载顺序

### 8、自动配置原理

#### 1、自动配置原理

1、SpringBoot启动的是加载主配置类，开启自动配置功能@EnableAutoConfiguration

2、@EnableAutoConfiguration的作用

	- 利用EnableAutoConfigurationImportSelector给容器导一些组件？

 - 可以插件selectImports()方法的内容

 - List<String> configurations = getCandidateConfigurations(annotationMetadata,
   attributes);获取候选的配置

    - ```java
      SpringFactoriesLoader.loadFactoryNames()
      // 描所有jar包类路径下 META- INF/spring. factories
      // 把少秒到的这些文件的内容包装成properties对象
      // 从properties总获取到EnableAutoConfiguration.class类对应的值，把他们添加再容器中
      // 
      ```

   **将类路径下的META-INF/spirng.factories 里的配置的所有EnableAutoConfiguration的值加入到容器中**

   ​	每一个 xxxAutoConfiguration类都是容器的一个组件,都加入到容器中,用他们来做自动配置

3、每一个自动配置类进行自动配置功能

4、以**HttpEncodingAutoConfiguration**为例解释自动配置原理

```java
@Configuration(
    proxyBeanMethods = false
)
@EnableConfigurationProperties({ServerProperties.class})
@ConditionalOnWebApplication(
    type = Type.SERVLET
)
@ConditionalOnClass({CharacterEncodingFilter.class})
@ConditionalOnProperty(
    prefix = "server.servlet.encoding",
    value = {"enabled"},
    matchIfMissing = true
)
public class HttpEncodingAutoConfiguration {
```



5、所有在配置文件能配置的属性都是在xxxxProperties类中封装着



……



精髓：

​	**1). SpringBoot启动会加载大量的自动配置类**

​	**2).我们看我们需要的功能有没有SpringBoot默认写好的自动配置类;**



​	**3).我们再来看这个自动配置类中到底配置了哪些组件; ( 只要我们要用的组件有.我们就不需要再来配置了)**



**4)、给容器中自动配置类添加组件的时候,会从properties类中获取某些属性。我们就可以在配置文件中指定这些属性的值;**



xxxxAutoConfigurartion :自动配置类;
给容器中添加组件
xxxxProperties:封装配置文件中相关属性;

#### 2、细节

##### 1、@Conditional派生注解 ( Spring注解版原生的@Conditional作用)

作用:必须是@Conditional指定的条件成立,才给容器中添加组件,配置配里面的所有内容才生效;



##### 自动配置类必须在一定的条件下才能生效;

我们怎么知道哪些自动配置类生效;
我们可以通过启用debug-true属性;来让控制台打印自动配置报告,这样我们就可以很方便的知道哪些自动配置
类生效;

# 三、日志

## 1、日志框架

小张;开发-个大型系统;
1、System.out.println("") ;将关键数据打印在控制台;去掉?写在一个文件?
2、框架来记录系统的一-些运行时信息;日志框架; zhanglogging.jar ;
3、大上的几个功能?异步模式?自动归档? xxx? zhanglogging-good.jar ?
4、将以前框架卸下来?换上新的框架,重新修改之前相关的API ; zhanglogging-prefect.jar ;
5、JDBC--- 数据库驱动;
写了一个统一-的接口层;日志门面(日志的一个抽象层) ; logging-abstract.jar ;
给项目中导入具体的日志实现就行了;我们之前的日志框架的都是实现的抽象层



市面上的日志框架

JUL、JCL、Jbosslogging、logback. log4j、 log4j2、 sf...

| 日志门面（日志的抽象层）                                     | 日志的实现                                              |
| ------------------------------------------------------------ | ------------------------------------------------------- |
| ~~JCL (Jakarta Commons Logging)~~ <br />SLF4j ( Simple Logging<br/>Facadel for Java )<br/>~~jboss-logging~~ | Log4j JUL ( java.til.logging )<br/>Log4j2 <br />Logback |

左边选-一个门面(抽象层)、右边来选一个实现;

日志门面：SLF4J

日志实现：Logback



SpringBoot :底层是Spring框架, Spring框架默认是用JCL ;'
SpringBoot选用SL F4j和logback ;

## 2、SLF4J使用

#### 1、如何在系统中使用SLF4J

以后开发的时候,日志记录方法的调用,不应该来直接调用日志的实现类,而是调用日志抽象层里面的方法;

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    logger.info("Hello World");
  }
}
```

每一个日志的实现框架都有自己的配置文件。使用sIf4j以后 ,配置文件还是做成日志实现框架自己本身的配置文件;



#### 2、遗留问题

a ( slf4j+logback ) : Spring ( commons-logging)、Hibernate ( jboss-logging)、MyBatis、 xXXX
统一日志记录,即使是别的框架和我一起统一使用sIf4j进行输出 ?



##### 如何让系统中所有的日志都统一到sIf4j ;

1、将系统中其他日志框架先排除出去;
2、用中间包来替换原有的日志框架;
3、我们导入slf4j其他的实现

#### 3、SpringBoot日志关系

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter</artifactId>
  <version>2.3.1.RELEASE</version>
  <scope>compile</scope>
</dependency>
```

SpringBoot使用它来做日志功能

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-logging</artifactId>
  <version>2.3.1.RELEASE</version>
  <scope>compile</scope>
</dependency>
```

总结:
1 )、SpringBoot底层也是使用slf4j+logback的方式进行日志记录

2)、SpringBoo也把其他的日志都替换成了slf4j ; 

3)、中间替换包?  

4)、如果我们要引入其他框架?-定要把这个框架的默认日志依赖移除掉?



**SpringBoot能自动适配所有的日志,而且底层使用slf4j+logback的方式记录日志,引入其他框架的时候,只需要把这个框架依赖的日志框架排除掉;**

#### 4、日志使用

```java
// 记录器
Logger logger = LoggerFactory.getLogger(getClass());

@Test
void contextLoads() {

    // 日志级别
    // 由高到低 trace < debug < info < warn < error
    // 可以调整输出的日志级别： 日志就只会在这个级别以后的高级别生效

    logger.trace(() -> "这是trace日志");
    logger.debug(() -> "这是debug日志");
    // spring默认给我们使用的是info级别的
    logger.info(() -> "这是info日志");
    logger.warn(() -> "这是warn日志");
    logger.error(() -> "这是error日志");
```

```properties
# 指定日志的level
logging.level.com.ahhtou=trace

# 当前项目下生成springboot.log日志
# 可以指定完整的路径
#logging.file.name=springboot.log

# 在当前磁盘的根路径下创建spring文件夹和里面的log文件夹;使用spring. log作为默认
# logging.file.path=/spring/log

logging.pattern.console=%d{yyyy-MM-dd} ~ [Thread -> %thread] %-5level %logger{50} - %msg%n
```



日志输出格式:

%d表示日期时间，

%thread表示线程名，

%-5level :级别从左显示5个字符宽度

%logger{50}表示logger名字最长50个字符，否则按照句点分割。

%msg :日志消息，

%n是换行符

| logging.file | logging.path | Example  | Description                        |
| ------------ | ------------ | -------- | ---------------------------------- |
| (none)       | (none)       |          | 只在控制台输出                     |
| 指定文件名   | (none)       | my.log   | 输出日志到my.log文件               |
| (none)       | 指定目录     | /var/log | 输出到指定目录的 spring.log 文件中 |

#### 5、指定配置

给类路径下放上每个日志框架自己的配置文件即可；SpringBoot就不使用他默认配置的了

| Logging System          | Customization                                                |
| ----------------------- | ------------------------------------------------------------ |
| Logback                 | `logback-spring.xml`, `logback-spring.groovy`, `logback.xml` or `logback.groovy` |
| Log4j2                  | `log4j2-spring.xml` or `log4j2.xml`                          |
| JDK (Java Util Logging) | `logging.properties`                                         |

logback.xml：直接就被日志框架识别了；

**logback-spring.xml**：日志框架就不直接加载日志的配置项，由SpringBoot解析日志配置，可以使用SpringBoot的高级Profile功能

```xml
<springProfile name="staging">
    <!-- configuration to be enabled when the "staging" profile is active -->
      可以指定某段配置只在某个环境下生效
</springProfile>
Copy to clipboardErrorCopied
```

如：

```xml
<appender name="stdout" class="ch.qos.logback.core.ConsoleAppender">
        <!--
        日志输出格式：
            %d表示日期时间，
            %thread表示线程名，
            %-5level：级别从左显示5个字符宽度
            %logger{50} 表示logger名字最长50个字符，否则按照句点分割。 
            %msg：日志消息，
            %n是换行符
        -->
        <layout class="ch.qos.logback.classic.PatternLayout">
            <springProfile name="dev">
                <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} ----> [%thread] ---> %-5level %logger{50} - %msg%n</pattern>
            </springProfile>
            <springProfile name="!dev">
                <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} ==== [%thread] ==== %-5level %logger{50} - %msg%n</pattern>
            </springProfile>
        </layout>
    </appender>Copy to clipboardErrorCopied
```

如果使用logback.xml作为日志配置文件，还要使用profile功能，会有以下错误

```
no applicable action for [springProfile]
```

#### 6、切换日志框架

可以按照slf4j的日志适配图，进行相关的切换；

slf4j+log4j的方式；

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
  <exclusions>
    <exclusion>
      <artifactId>logback-classic</artifactId>
      <groupId>ch.qos.logback</groupId>
    </exclusion>
    <exclusion>
      <artifactId>log4j-over-slf4j</artifactId>
      <groupId>org.slf4j</groupId>
    </exclusion>
  </exclusions>
</dependency>

<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-log4j12</artifactId>
</dependency>
Copy to clipboardErrorCopied
```

切换为log4j2

```xml
   <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
                <exclusion>
                    <artifactId>spring-boot-starter-logging</artifactId>
                    <groupId>org.springframework.boot</groupId>
                </exclusion>
            </exclusions>
        </dependency>

<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```

# 四、Web开发

1. 创建SpringBoot应用，选中我们需要的模块
2. SpringBoot已经默认将这些场景配置好了，只需要在配置文件中指定少量配置就可以运行起来
3. 自己编写业务代码

## web自动配置规则

1. WebMvcAutoConfiguration
2. WebMvcProperties
3. ViewResolver自动配置
4. 静态资源自动映射
5. Formatter与Converter自动配置
6. HttpMessageConverter自动配置
7. 静态首页
8. favicon
9. 错误处理

## SpringBoot对静态资源的映射规则

`WebMvcAutoConfiguration`类的`addResourceHandlers`方法：（添加资源映射）

```java
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            if (!this.resourceProperties.isAddMappings()) {
                logger.debug("Default resource handling disabled");
            } else {
                Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
                CacheControl cacheControl = this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl();
                if (!registry.hasMappingForPattern("/webjars/**")) {
                    this.customizeResourceHandlerRegistration(registry.addResourceHandler(new String[]{"/webjars/**"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/webjars/"}).setCachePeriod(this.getSeconds(cachePeriod)).setCacheControl(cacheControl));
                }

                String staticPathPattern = this.mvcProperties.getStaticPathPattern();
                if (!registry.hasMappingForPattern(staticPathPattern)) {
                    this.customizeResourceHandlerRegistration(registry.addResourceHandler(new String[]{staticPathPattern}).addResourceLocations(WebMvcAutoConfiguration.getResourceLocations(this.resourceProperties.getStaticLocations())).setCachePeriod(this.getSeconds(cachePeriod)).setCacheControl(cacheControl));
                }

            }
        }Copy to clipboardErrorCopied
```

所有 `/webjars/**` ，都去 `classpath:/META-INF/resources/webjars/` 找资源

`webjars`：以jar包的方式引入静态资源；

```xml
<!-- 引入Jquery -->
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>jquery</artifactId>
    <version>3.4.1</version>
</dependency>
```

访问地址对应就是：http://localhost:8080/webjars/jquery/3.4.1/jquery.js



### 非webjars，自己的静态资源怎么访问

| 数组中的值                     | 在项目中的位置                         |
| ------------------------------ | -------------------------------------- |
| classpath:/META-INF/resources/ | src/main/resources/META-INF/resources/ |
| classpath:/resources/          | src/main/resources/resources/          |
| classpath:/static/             | src/main/resources/static/             |
| classpath:/public/             | src/main/resources/public/             |

localhost:8080/abc ---> 去静态资源文件夹里面找abc

`location`就是静态资源路径，所以欢迎页的页面就是上面静态资源下的`index.html`，被`/**`映射，因此直接访问项目就是访问欢迎页

配置其他的静态文件

```properties
spring.resources.static-locations=classpath:/hello/, classpath:/ahhTou/
```

### 网站图标映射 favicon.ico

所有的 favicon.ico 都是在静态资源文件下找；



## 模板引擎

常见的模板引擎有`JSP`、`Velocity`、`Freemarker`、`Thymeleaf`

SpringBoot推荐使用Thymeleaf；

### 引入thymeleaf

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

**如需切换thymeleaf版本：**

```xml
<properties>

        <thymeleaf.version>X.X.X.RELEASE</thymeleaf.version>

        <!-- 布局功能的支持程序  thymeleaf3主程序  layout2以上版本 -->
        <!-- thymeleaf2   layout1-->
        <thymeleaf-layout-dialect.version>2.2.2</thymeleaf-layout-dialect.version>

  </properties>Copy to clipboardErrorCopied
```

### Thymeleaf使用

```java
package org.springframework.boot.autoconfigure.thymeleaf;

......

@ConfigurationProperties(
    prefix = "spring.thymeleaf"
)
public class ThymeleafProperties {
    private static final Charset DEFAULT_ENCODING;
    public static final String DEFAULT_PREFIX = "classpath:/templates/";
    public static final String DEFAULT_SUFFIX = ".html";
    private boolean checkTemplate = true;
    private boolean checkTemplateLocation = true;
    private String prefix = "classpath:/templates/";
    private String suffix = ".html";
    private String mode = "HTML";Copy to clipboardErrorCopied
```

默认只要我们把HTML页面放在`classpath:/templates/`，thymeleaf就能自动渲染；

1. 创建模板文件`t1.html`，并导入thymeleaf的名称空间

   ```html
   <html lang="en" xmlns:th="http://www.thymeleaf.org">Copy to clipboardErrorCopied
   ```

   ```html
   <!DOCTYPE html>
   <html lang="en" xmlns:th="http://www.thymeleaf.org">
   <head>
       <meta charset="UTF-8">
       <title>Title</title>
   </head>
   <body>
   
   </body>
   </html>Copy to clipboardErrorCopied
   ```

2. 使用模板

   ```html
   <!DOCTYPE html>
   <html lang="en" xmlns:th="http://www.thymeleaf.org">
   <head>
       <meta charset="UTF-8">
       <title>[[${title}]]</title>
   </head>
   <body>
   <h1 th:text="${title}"></h1>
   <div th:text="${info}">这里的文本之后将会被覆盖</div>
   </body>
   </html>Copy to clipboardErrorCopied
   ```

3. 在controller中准备数据

   ```java
   @Controller
   public class HelloT {
   
       @RequestMapping("/ht")
       public String ht(Model model) {
           model.addAttribute("title","hello Thymeleaf")
                .addAttribute("info","this is first thymeleaf test");
           return "t1";
       }
   }Copy to clipboardErrorCopied
   ```

### 语法规则

`th:text` --> 改变当前元素里面的文本内容；

`th：任意html属性` --> 来替换原生属性的值

## SpringMVC自动配置

Spring Boot为Spring MVC提供了自动配置，可与大多数应用程序完美配合。

以下是SpringBoot对SpringMVC的默认配置

**`org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration`**

自动配置在Spring的默认值之上添加了以下功能：

- 包含`ContentNegotiatingViewResolver`和`BeanNameViewResolver`。--> 视图解析器
- 支持服务静态资源，包括对WebJars的支持（[官方文档中有介绍](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-static-content)）。--> 静态资源文件夹路径
- 自动注册`Converter`，`GenericConverter`和`Formatter`beans。--> 转换器，格式化器
- 支持`HttpMessageConverters`（[官方文档中有介绍](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-message-converters)）。--> SpringMVC用来转换Http请求和响应的；User---Json；
- 自动注册`MessageCodesResolver`（[官方文档中有介绍](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-message-codes)）。--> 定义错误代码生成规则
- 静态`index.html`支持。--> 静态首页访问
- 定制`Favicon`支持（[官方文档中有介绍](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-favicon)）。--> 网站图标
- 自动使用`ConfigurableWebBindingInitializer`bean（[官方文档中有介绍](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-web-binding-initializer)）。

如果您想保留 Spring Boot MVC 的功能，并且需要添加其他 [MVC 配置](https://docs.spring.io/spring/docs/5.1.3.RELEASE/spring-framework-reference/web.html#mvc)（拦截器，格式化程序和视图控制器等），可以添加自己的 `WebMvcConfigurer` 类型的 `@Configuration` 类，但**不能**带 `@EnableWebMvc` 注解。如果您想自定义 `RequestMappingHandlerMapping`、`RequestMappingHandlerAdapter` 或者 `ExceptionHandlerExceptionResolver` 实例，可以声明一个 `WebMvcRegistrationsAdapter` 实例来提供这些组件。

如果您想完全掌控 Spring MVC，可以添加自定义注解了 `@EnableWebMvc` 的 @Configuration 配置类。

### 视图解析器

视图解析器：根据方法的返回值得到视图对象（View），视图对象决定如何渲染（转发？重定向？）

- 自动配置了ViewResolver
- ContentNegotiatingViewResolver：组合所有的视图解析器的；

![1573873741438](https://cdn.static.note.zzrfdsn.cn/images/springboot/assets/1573873741438.png)

视图解析器从哪里来的？

![1573874365778](https://cdn.static.note.zzrfdsn.cn/images/springboot/assets/1573874365778.png)

**所以我们可以自己给容器中添加一个视图解析器；自动的将其组合进来**

```java
    @Bean
    public ViewResolver myViewResolver() {
        
        return new MyViewResolver();

    }
	@Component
	public class MyViewResolver implements ViewResolver {

    	@Override
    	public View resolveViewName(String s, Locale locale) throws Exception {
        return null;
    	}
	}
```

![1573875409759](https://cdn.static.note.zzrfdsn.cn/images/springboot/assets/1573875409759.png)

### 转换器、格式化器

- `Converter`：转换器； public String hello(User user)：类型转换使用Converter（表单数据转为user）
- `Formatter` 格式化器； 2017.12.17===Date；

```java
        @Bean
        //在配置文件中配置日期格式化的规则
        @ConditionalOnProperty(prefix = "spring.mvc", name = "date-format")
        public Formatter<Date> dateFormatter() {
            return new DateFormatter(this.mvcProperties.getDateFormat());//日期格式化组件
        }Copy to clipboardErrorCopied
```

**自己添加的格式化器转换器，我们只需要放在容器中即可**

### HttpMessageConverters

- `HttpMessageConverter`：SpringMVC用来转换Http请求和响应的；User---Json；
- `HttpMessageConverters` 是从容器中确定；获取所有的HttpMessageConverter；

**自己给容器中添加HttpMessageConverter，只需要将自己的组件注册容器中（@Bean,@Component）**

### MessageCodesResolver

**我们可以配置一个ConfigurableWebBindingInitializer来替换默认的；（添加到容器）**

### 扩展SpringMVC

以前的配置文件中的配置

```xml
<mvc:view-controller path="/hello" view-name="success"/>Copy to clipboardErrorCopied
```

**现在，编写一个配置类（@Configuration），是WebMvcConfigurer类型；不能标注@EnableWebMvc**

```java
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/hi").setViewName("success");
    }
}Copy to clipboardErrorCopied
```

访问：http://localhost:8080/hi

**原理：**

我们知道`WebMvcAutoConfiguration`是SpringMVC的自动配置类

下面这个类是`WebMvcAutoConfiguration`中的一个内部类

![1573891167026](https://cdn.static.note.zzrfdsn.cn/images/springboot/assets/1573891167026.png)

看一下`@Import({WebMvcAutoConfiguration.EnableWebMvcConfiguration.class})`中的这个类，

这个类依旧是`WebMvcAutoConfiguration`中的一个内部类

![1573891478014](https://cdn.static.note.zzrfdsn.cn/images/springboot/assets/1573891478014.png)

重点看一下这个类继承的父类`DelegatingWebMvcConfiguration`

```java
public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport {
    private final WebMvcConfigurerComposite configurers = new WebMvcConfigurerComposite();

    public DelegatingWebMvcConfiguration() {
    }

    //自动注入，从容器中获取所有的WebMvcConfigurer
    @Autowired(
        required = false
    )
    public void setConfigurers(List<WebMvcConfigurer> configurers) {
        if (!CollectionUtils.isEmpty(configurers)) {
            this.configurers.addWebMvcConfigurers(configurers);
        }

    }

    ......

    /**
     * 查看其中一个方法
      * this.configurers：也是WebMvcConfigurer接口的一个实现类
      * 看一下调用的configureViewResolvers方法 ↓
      */
    protected void configureViewResolvers(ViewResolverRegistry registry) {
        this.configurers.configureViewResolvers(registry);
    }Copy to clipboardErrorCopied
    public void configureViewResolvers(ViewResolverRegistry registry) {
        Iterator var2 = this.delegates.iterator();

        while(var2.hasNext()) {
            WebMvcConfigurer delegate = (WebMvcConfigurer)var2.next();
            //将所有的WebMvcConfigurer相关配置都来一起调用；  
            delegate.configureViewResolvers(registry);
        }

    }Copy to clipboardErrorCopied
```

容器中所有的WebMvcConfigurer都会一起起作用；

我们的配置类也会被调用；

效果：SpringMVC的自动配置和我们的扩展配置都会起作用；

![1573892805539](https://cdn.static.note.zzrfdsn.cn/images/springboot/assets/1573892805539.png)

### 全面接管SpringMVC

SpringBoot对SpringMVC的自动配置不需要了，所有都是由我们自己来配置；所有的SpringMVC的自动配置都失效了

**我们只需要在配置类中添加`@EnableWebMvc`即可；**

```java
@Configuration
@EnableWebMvc
public class MyMvcConfig implements WebMvcConfigurerCopy to clipboardErrorCopied
```

![1573892899452](https://cdn.static.note.zzrfdsn.cn/images/springboot/assets/1573892899452.png)

原理：

为什么@EnableWebMvc自动配置就失效了；

我们看一下EnableWebMvc注解类

```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({DelegatingWebMvcConfiguration.class})
public @interface EnableWebMvc {
}Copy to clipboardErrorCopied
```

重点在于`@Import({DelegatingWebMvcConfiguration.class})`

`DelegatingWebMvcConfiguration`是`WebMvcConfigurationSupport`的子类

我们再来看一下springmvc的自动配置类`WebMvcAutoConfiguration`

```java
@Configuration(
    proxyBeanMethods = false
)
@ConditionalOnWebApplication(
    type = Type.SERVLET
)
@ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class})

//重点是这个注解，只有当容器中没有这个类型组件的时候该配置类才会生效
@ConditionalOnMissingBean({WebMvcConfigurationSupport.class})

@AutoConfigureOrder(-2147483638)
@AutoConfigureAfter({DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class, ValidationAutoConfiguration.class})
public class WebMvcAutoConfiguration Copy to clipboardErrorCopied
```

1. @EnableWebMvc将WebMvcConfigurationSupport组件导入进来；
2. 导入的WebMvcConfigurationSupport只是SpringMVC最基本的功能；

### 如何修改SpringBoot的默认配置

SpringBoot在自动配置很多组件的时候，先看容器中有没有用户自己配置的（@Bean、@Component）如果有就用用户配置的，如果没有，才自动配置；如果有些组件可以有多个（ViewResolver）将用户配置的和自己默认的组合起来；

- 在SpringBoot中会有非常多的xxxConfigurer帮助我们进行扩展配置
- 在SpringBoot中会有很多的xxxCustomizer帮助我们进行定制配置



# 整合Mybatis

```xml
    <dependency>  
            <groupId>org.springframework.boot</groupId>  
            <artifactId>spring-boot-starter-thymeleaf</artifactId>  
    </dependency> 
        
    <dependency>  
            <groupId>mysql</groupId>  
            <artifactId>mysql-connector-java</artifactId>  
    </dependency>  
        
    <dependency>  
            <groupId>org.mybatis.spring.boot</groupId>  
            <artifactId>mybatis-spring-boot-starter</artifactId>  
            <version>1.3.0</version>  
    </dependency>  
```

