### SpirngMVC

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