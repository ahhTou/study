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



#### 2、yml支持多文档块文件

#### 3、激活指定profile