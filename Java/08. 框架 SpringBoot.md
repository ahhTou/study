# 原理



自动配置

pom.xml

- ```xml
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.3.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    ```

- ```xml
    <parent>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-dependencies</artifactId>
      <version>2.3.3.RELEASE</version>
    </parent>
    ```

- 我们在写或者引入一些SPringboot依赖的时候，不需要指定版本，就因为有这些版本仓库



启动器

- ```java
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    ```

- SpringBoot的启动场景
- 比如spring-boot-starter-web，他就会帮我们自动导入web环境所有的依赖!
- springboot会将所有的功能场景，都变成一个个的启动器
- 我们要使用什么功能，就找到对应的启动器了



## 主程序

```java
@SpringBootApplication // 标准这个类是一个Springboot的应用
public class Springboot01Application {

    public static void main(String[] args) {

        // 将Springboot启动
        SpringApplication.run(Springboot01Application.class, args);
    }

}
```



- 注解

    - ```java
        @SpringBootConfiguration // Spring的配置
        	@Configuration // Spring配置类
        		@Component 	// 这也是一个spring的组件
        
        @EnableAutoConfiguration // 自动配置
        	@AutoConfigurationPackage // 自动配置包
        		@Import({Registrar.class})// 导入注册器
        	@Import({AutoConfigurationImportSelector.class}) // 导入选择器	
        ```

    - @Import({AutoConfigurationImportSelector.class}) 中

        ```java
        List<String> configurations = this.getCandidateConfigurations(annotationMetadata, attributes);
        
        // 获取候选的配置
            protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
                List<String> configurations = SpringFactoriesLoader.loadFactoryNames(this.getSpringFactoriesLoaderFactoryClass(), this.getBeanClassLoader());
                Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories. If you are using a custom packaging, make sure that file is correct.");
                return configurations;
            }
        ```

    

    结论: springboot所有自动配置都是在启的时候扫描并加载: spring.factories所有的自动配置类都在这里
    面，但是不一定生效Ⅰ要判断条件是否成立，只要导入了对应的start，就有对应的启动器了，有了启动器，我们自动装配就会生效，然后就配置成功!



- springboot在启动时候，从类路径下 /META-INF/spring.factories 获取指定的值
- 将这些自动配置的类导入容器，自动配置就会生效，帮我们自动配置
- 以前我们u需要制动配置的东西，springboot帮我们做了
- 整合JavaEE，解决方案和自动配置东西都在spring-boot-autoconfigure-2.3.3.RELEASE.jar包下
- 他会把所有需要导入的组件，以类名的方式返回，这些组件就会被添加到容器



# Config

## 语法

```yaml
# 普通的k-v
name: ahhTou

# 对象
student: 
  name: ahhTou
  age: 3
  
# 数组
pets:
  - cat
  - dog
  - pig

pets: [cat,dog,ping]
```



## 赋值

### 逐一赋值

```java
@Value("旺财")
private String dog;

@Value("3")
private Integer age;
```



### 通过yml统一绑定

java

```java
@ConfigurationProperties(prefix = "person")
public class Person {
    private String name;
    private Integer age;
    private Boolean happy;
    private Date birth;
    private Map<String, Object> maps;
    private List<Object> list;
    private Dog dog;

}
```

yml

```java
person:
  name: ahhTou
  age: 13
  happy: false
  birth: 2000/07/10
  maps: {k1: v1, k2: v2}
  list:
    - code
    - music
    - girl
  dog:
    name: 旺财
    age: 
```

### 自定义配置文件

只能逐一赋值

```java
@PropertySource(value = "classpath:ahhTou.properties")
public class Person {

    @Value("${name}")
    private String name;
    private Integer age;
    private Boolean happy;
    private Date birth;
    private Map<String, Object> maps;
    private List<Object> list;
    private Dog dog;

}
```

### yml的el表达式

```yml
person:
  name: ahhTou${random.uuid}}  # 随机名称
  hello: 大
  dog:
    name: ${person.hello:小}_旺财 # 如果hello没有值 则用hello的值，不存在固定值为小
    age: 3
```

### 松散绑定

**yml支持松散绑定**

松散绑定:这个什么意思呢?比如我的yml中写的last-name，这个和lastName是一样的，-后
面跟着的字母默认是大写的。这就是松散绑定

## 配置文件

### 优先级

- file: . /config/
- file: . /
- classpath:  /config/
- classpath:  /

### 切换配置文件

```java
spring:
  profiles:
    active: dev
```

application-dev.yaml

application-prod.yaml

### 切换配置代码块

```java
spring:
  profiles:
    active: dev
    
---
server:
  port: 8081
spring:
  profiles: dev
  
---
server:
  port: 8082
spring:
  profiles: prod
```



## 原理

这就是自动装配的原理!
精髓:

1. .SpringBoot启动会加载大量的自动配置类

2. 我们看我们需要的功能有没有在SpringBoot默认写好的自动配置类当中;

3. 我们再来看这个自动配置类中到底配置了哪些组件 (只要我们要用的组件存在在其中，我们就不要在手动配置了）

4. 给容器中自动配置类添加组件的时候，会从properties类中获取某些属性。我们只需要在配置文件中指定这些属性的值即可

    - xxxxAutoConfigurartion:自动配置类;给容器中添加组件
    - xxxxProperties:封装配置文件中相关属性;

    

可以通过debug=true来查看，那些自动配置类生效，那些自动配置类没有生效

```yml
debug: true
```



# 静态资源

存放位置

- http://localhost:8080/webjars/
    - webjars
- http://localhost:8080/
    - classpath: /public
    - classpath: /static
    - classpath: /resources
    - classpath: /META-INF/resources

优先级

- resouces
- static（默认）
- public

# 拦截器

- 写一个拦截器

```java
@Configuration
public class MyWebConfigurer implements WebMvcConfigurer {

    @Resource
    private LoginHandlerInterceptor loginHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginHandlerInterceptor).addPathPatterns("/*").excludePathPatterns("hello/*");
    }
}
```



- 添加到mvc中

```java
@Configuration
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return false;
    }
}
```

# 数据源

properties.yml

```
spring:
  datasource:
    username: root
    password: 123
    url: jdbc:mysql://localhost:3306/hello?serverTimezone=GMT%2B8
    driver-class-name: com.mysql.cj.jdbc.Driver
```



## 整合Druid

```java
spring:
  datasource:
    username: root
    password: 123
    url: jdbc:mysql://localhost:3306/hello?serverTimezone=GMT%2B8
    driver-class-name: com.mysql.cj.jdbc.Driver
    type: com.alibaba.druid.pool.DruidDataSource
```

其他去看官网文档



## 整合Mybatis

配置扫描

```java
@MapperScan(basePackages = {"com.ahhTou.mapper"}) //扫描的mapper
@SpringBootApplication
public class HelloApplication {


    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }

}
```

配置

```yml
mybatis:
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.ahhTou.bean
  configuration:
    cache-enabled: true
    local-cache-scope: statement
    aggressive-lazy-loading: false
    lazy-loading-enabled: true
    map-underscore-to-camel-case: true
    jdbc-type-for-null: null
```

映射文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- 映射的接口 -->
<mapper namespace="com.ahhTou.mapper.UserMapper">
    
    <!-- 配置缓存 -->
    <cache type="com.ahhTou.conf.MybatisRedisCache">
        <property name="" value=""/>
    </cache>

    <!-- 对应的sql -->
    <select id="对应的方法名" resultType="">
    </select>

</mapper>
```
