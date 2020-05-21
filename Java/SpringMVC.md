### SpirngMVC

## Day1

> 对应包 U1_helloWorld

#### 约束

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/mvc
       http://www.springframework.org/schema/mvc/spring-mvc.xsd">
</beans>
```

#### 流程

1. 启动服务器，加载配置文件

   DispatcherServlet对象被创建

   SpirngMVC.xml被加载

   HelloController创建成对象

   ###### web.xml

   ```xml
   <servlet>
     <servlet-name>dispatcherServlet</servlet-name>
     <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
   
     <!-- 初始化参数 -->
     <init-param>
       <param-name>contextConfigLocation</param-name>
       <param-value>classpath:SpringMVC.xml</param-value>
     </init-param>
   
     <load-on-startup>1</load-on-startup>
   </servlet>
   ```

2. 发送请求，后台处理请求

   > 请求路径为 param/testParam 并返回到 success.jsp

```java
@Controller
@RequestMapping("/param")
public class ParamController {
    @RequestMapping("/testParam")
    public String testParam(String username, String password){
        return "success";
    }
```

#### 解决中文乱码

> web.xml 中<web-app>标签中

```xml
<!--  配置解决中文乱码的过滤器-->
<filter>
  <filter-name>characterEncodingFilter</filter-name>
  <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
  <init-param>
    <param-name>encoding</param-name>
    <param-value>UTF-8</param-value>
  </init-param>
</filter>

<filter-mapping>
  <filter-name>characterEncodingFilter</filter-name>
  <url-pattern>/*</url-pattern>
</filter-mapping>
```

#### @RequestMapping

```java
// 效果相同

@RequestMapping(path = "/testRequestMapping")

@RequestMapping(value = "/testRequestMapping")

@RequestMapping("/testRequestMapping")

@RequestMapping(
    value = "/testRequestMapping",	// 路径为 /testRequestMapping
    params = {"username=ahhTou"},	// 必须有值，而且只为ahhTou
    method = {RequestMethod.POST},  // 提交方法必须为POST
    headers = {"Accept"}			// 必须有响应头Accept
)
```

#### 自动封装数据

> 自动封装为Account

jsp

```jsp
姓名：<input type="text" name="username"><br/>
密码：<input type="text" name="password"><br/>
金额：<input type="text" name="money"><br/>

<%-- 封装对象到另一个对象中 --%>
name:<input type="text" name="user.name"><br/>
age:<input type="text" name="user.age"><br/>

<%-- 封装lsit --%>
name:<input type="text" name="list[0].name"><br/>
age:<input type="text" name="list[0].age"><br/>

<%-- 封装map --%>
name:<input type="text" name="map['one'].name"><br/>
age:<input type="text" name="map['one'].age"><br/>
<input type="submit" name="提交"><br/>
```

java

```java
public String saveAccount(Account account){
    System.out.println(account);
    return "success";
}
```

#### 指定类型转换器

>  SpringMVC.xml

```xml
    <!-- 配置自定义类型转换器 -->
    <bean id="conversionService" class="org.springframework.context.support.ConversionServiceFactoryBean">
        <property name="converters">
            <set>
                <bean class="U1_helloWorld.utils.StringToDateConverter"/>
            </set>
        </property>
    </bean>

    <!-- 开启SpringMVC框架注解支持 -->
    <mvc:annotation-driven conversion-service="conversionService"/>
```

>  StringToDateConverter

```java
//需要事先Converter<T, T> 的接口 

public class StringToDateConverter implements Converter<String, Date> {

    /**
     *
     * @param s 传入来的字符串
     * @return
     */
    @Override
    public Date convert(String s) {
        if (s == null){
            throw new RuntimeException("请传入数据");
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 把日期转化为字符串
            return df.parse(s);
        } catch (ParseException e) {
            throw new RuntimeException("数据类型转换出现异常");
        }
    }
}
```

#### 获取原生Servlet原生API

```java
@RequestMapping("testServlet")
public String testServlet(HttpServletRequest request, HttpServletResponse response){
    System.out.println(request);
    System.out.println(response);
    return "success";
}
```

#### @RequestParam

> 作用：
>
> 把请求中指定名称的参数给控制器中的形参赋值。
>
> 属性：
>
> value:请求参数中的名称。
> required:请求参数中是否必须提供此参数。默认值: true。表示必须提供，如果不提供将报错。

后

```java
@RequestMapping("/testRequestParam")
public String testRequestParam(@RequestParam(name="name") String username){
    System.out.println("执行了");
    System.out.println(username);
    return  "success";
}
```

前

```jsp
<a href="anno/testRequestParam?name=ahhTou">RequestParam</a>
```

#### @RequestBody

> 用于获取请求体内容。直接使用得到是key=value&key=value.. .结构的数据。
> get请求方式不适用。

```java
@RequestMapping("/testRequestBody")
public String testRequestBody(@RequestBody String body) throws UnsupportedEncodingException {
    System.out.println(body);
    return  "success";
}
```

```jsp
<form action="anno/testRequestBody" method="post">
    name:<input type="text" name=name"><br/>
    age:<input type="text" name="age"><br/>
    <input type="submit" name="提交"><br/>
</form>
```

#### @PathVariable

> 作用：
>
> 用于绑定url中的占位符。例如:请求url中/delete/{id},这个{id}就是ur1占位符。
> url支持占位符是spring3.0之后加入的。是springmvc支持rest风格URL的一个重要标志。
>
> 属性：
>
> value:用于指定url中占位符名称。
> required:是否必须提供占位符。

```jsp
<a href="anno/testPathVariable/10">user/testVariable</a>
```

```
@RequestMapping("/testPathVariable/{sid}")
public String testPathVariable(@PathVariable(name = "sid") String id){
    System.out.println(id);
    return  "success";
}
```

#### @RequestHeader

>作用:
>用于获取请求消息头。
>属性:
>value:提供消息头名称
>required:是否必须有此消息头
>注:
>在实际开发中一般不怎么用。

```java
@RequestMapping("/testRequestHeader")
public String testRequestHeader(@RequestHeader(value = "Accept") String header){
    System.out.println(header);
    return  "success";
}
```

```jsp
<a href="anno/testRequestHeader">user/testRequestHeader</a>
```

#### @CookieValue

> 作用:
> 用于把指定cookie名称的值传入控制器方法参数。
> 属性:
> value:指定cookie的名称。
> required:是否必须有此cookie.

#### @ModelAttribute

> 作用: 
> 该注解是Spri ngMVC4.3版本以后新加入的。它可以用于修饰方法和参数。
> 出现在方法上，表示当前方法会在控制器的方法执行之前，先执行。它可以修饰没有返回值的方法，也可以修饰有具体返回值的方法。出现在参数上，获取指定的数据给参数赋值。
>
> 属性:
> value:用于获取数据的key. key可以是POJO的属性名称，也可以是map结构的keyo
>
> 应用场景:
> 当表单提交数据不是完整的实体类数据时，保证没有提交数据的字段使用数据库对象原来的数据。
> 例如:
> 我们在编辑一个用户时，用户有一个创建信息字段，该字段的值是不允许被修改的。在提交表单数
> 据是肯定没有此字段的内容，- - 旦更新会把该字段内容置为nu1l,此时就可以使用此注解解决问题。

案例：提交无日期，控制台输出日期

```java
<form action="anno/testModelAttribute" method="post">
    name:<input type="text" name="name"><br/>
    age:<input type="text" name="age"><br/>
    <input type="submit" name="提交"><br/>
</form>
```

##### 有返回值

```java
@RequestMapping("/testModelAttribute")
public String testModelAttribute(User user) {
    System.out.println(user);
    return "success";
}
@ModelAttribute
public User showUser(String name) {
    System.out.println("showUser执行了");
    // 通过用户名查询数据库（模拟）
    User user = new User();
    user.setName(name);
    user.setAge(20);
    user.setDate(new Date());
    return user;
}
```

##### 无返回值

```java
@RequestMapping("/testModelAttribute")
public String testModelAttribute(@ModelAttribute("abc") User user) {
    System.out.println(user);
    return "success";
}
@ModelAttribute
public void showUser(String name, Map<String, User> map) {
    System.out.println("showUser执行了");
    // 通过用户名查询数据库（模拟）
    User user = new User();
    user.setName(name);
    user.setAge(20);
    user.setDate(new Date());
    map.put("abc",user);
}
```

#### @SessionAttribute

> 作用:
> 用于多次执行控制器方法间的参数共享。
>
> 属性:
> value:用于指定存入的属性名称
> type:用于指定存入的数据类型。