# Tomcat

## 配置端口号

```xml
<Connector port="8080" protocol="HTTP/1.1"
           connectionTimeout="20000"
           redirectPort="8443" />
```
## 配置Host

```java
  <Host name="localhost"  appBase="webapps"
        unpackWARs="true" autoDeploy="true">	
```





# Maven

项目架构管理工具

Maven的核心思想:**约定大于配置**

- 有约束，不要去违反

Maven会规定好你该如何去编写我们的ava代码，必须要按照这个规范来

**Maven的高级指出在于： 会自动后导入jar包所依赖的jar包**







# Servlet

## 1、简介

- Servlet就是sun公司开发动态web的一门技术
- Sun在这些API中提供一个接口叫做: Servlet，如果你想开发一个Servlet程序，只需要完成两个小步骤:
    - 编写一个类，实现Servlet接口
    - 把开发好的Java类部署到web服务器中

**把实现了Servlet接口的ava程序叫做，Servlet**

## 2、HelloServlet

1. 构建一个普通的Maven项目，删掉里面的src目录，以后我们的学习就在这个项目里面建立Moudel;这个空的工程就是Maven主工程;

2. 关于Maven父子工程的理解:

    父项目中会有

    ```xml
    <modules>
        <module>servlet-01</module>
    </modules>
    ```

    子项目中会有

    ```xml
    <parent>
        <artifactId>02-servlet</artifactId>
        <groupId>org.ahhtou</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    ```

3. Maven环境优化

    1. 修改web.xml为最新
    2. 讲maven的结构构建完整

4. 编写一个servlet程序

    1. 编写一个普通类

    2. 实现Servlet实现类，这里我们直接继承httpServlet

        ```java
        public class HelloServlet extends HttpServlet {
        
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                PrintWriter writer = resp.getWriter(); // 响应流
                writer.print("HelloServlet");
        
            }
            @Override
            protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                super.doPost(req, resp);
            }
        }
        ```

        

    5. 编写Servlet映射

    为什么需要映射:我们写的是JAVA程序，但是要通过浏览器访问，而浏览器需要连接web服务器，所以我们需要再web服务中注册我们写的Servlet，还需给他一个浏览器能够访问的路径;

    ```xml
    <!-- 注册servlet -->
    <servlet>
        <servlet-name>hello</servlet-name>
        <servlet-class>com.ahhTou.servlet.HelloServlet</servlet-class>
    </servlet>
    
    <servlet-mapping>
        <servlet-name>hello</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>
    ```

## 3、Servlet运行原理

请求通过servlet并调用service方法，并返回指定的相应

service方法由我们自己编写实现类，如上2.4

## 4、Mapping运行原理

1. 一个Servlet可以指定一个映射路径

    ```xml
    <servlet-mapping>
        <servlet-name>hello</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>
    ```

2. 一个Servlet可以指定多个映射路径

    ```xml
    <servlet-mapping>
        <servlet-name>hello</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>
    
    <servlet-mapping>
        <servlet-name>hello</servlet-name>
        <url-pattern>/helloPro</url-pattern>
    </servlet-mapping>
    ```

3. 一个Servlet可以指定通用映射路径

    ```java
    <servlet-mapping>
        <servlet-name>hello</servlet-name>
        <url-pattern>/hello/*</url-pattern>
    </servlet-mapping>
    ```

4. 默认路径

    ```xml
    <servlet-mapping>
        <servlet-name>hello</servlet-name>
        <url-pattern>/*</url-pattern>
    </servlet-mapping>
    ```

5. 指定后缀

    ```xml
    <servlet-mapping>
        <servlet-name>hello</servlet-name>
        <url-pattern>*.hello</url-pattern>
    </servlet-mapping>
    ```

6. 优先级

    普通的映射优先级 > 通配的映射优先级

## 5、ServletContext

web容器在启动的时候，它会为每个web程序都创建一个对应的servletContext对象，他代表的当前的web对象

### 1、对象

> servletContext可以做两个servlet之间的仓库

```java
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        this.getInitParameter();      初始化参数
//        this.getServletConfig();      Servlet 配置
//        this.getServletContext();     Servlet 上下文
        System.out.println("Hello");
        ServletContext servletContext = this.getServletContext(); // 上下文
        servletContext.setAttribute("username", "ahhTou");

        PrintWriter writer = resp.getWriter();
        writer.print("HelloServlet02");
    }
}
```

```java
public class GetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get");
        ServletContext servletContext = this.getServletContext();
        String username = (String) servletContext.getAttribute("username");
        PrintWriter writer = resp.getWriter();
        writer.print(username);
    }
}
```

### 2、初始化参数

设置

```xml
<context-param>
    <param-name>var1</param-name>
    <param-value>key1</param-value>
</context-param>
```

获取

```java
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ServletContext context = this.getServletContext();
    String var1 = context.getInitParameter("var1");
    resp.getWriter().print(var1);

}
```

### 3、转发

```java
ServletContext context = this.getServletContext();
context.getRequestDispatcher("/gp").forward(req, resp);
```

#### 4、读取资源文件

Properties

- 在java目录下新建properties
- 在resources目录下新建properties

发现 ： 都被打包到了同一个路径下: classes，我们俗称这个路径为classpath

```java
System.out.println("demo5");
InputStream is = this.getServletContext().getResourceAsStream("/WEB-INF/classes/db.properties");
InputStream is2 = this.getServletContext().getResourceAsStream("/WEB-INF/classes/com/ahhTou/servlet/db.properties");

Properties properties = new Properties();
Properties properties2 = new Properties();
properties.load(is);
String u = properties.getProperty("username");
String password = properties.getProperty("password");
properties2.load(is2);
String u1 = properties2.getProperty("username");
String p2 = properties2.getProperty("password");
resp.getWriter().append(u).append("===").append(password).append("===").append(u1).append("===").append(p2);
```

## 6、HttpServletResponse

web服务器接收到客户端的http请求，针对这个请求，分别创建一个代表请求的HttpServletRequest
对象，代表响应的一个HttpServletResponse;

- 如果要获取客户端请求过来的参数:找HttpServletRequest
- 如果要给客户端响应一些信息:找HttpServletResponse

### 简单分类

- 负责向浏览器发送数据的方法

```java
ServletOutputStream getOutputStream() throws IOException; // 写流
PrintWriter getWriter() throws IOException; // 写中文
```

- 负责向浏览器发送响应头的方法

```java
void setCharacterEncoding(String var1);

void setContentLength(int var1);

void setContentLengthLong(long var1);

void setContentType(String var1);

void setDateHeader(String var1, long var2);

void addDateHeader(String var1, long var2);

void setHeader(String var1, String var2);

void addHeader(String var1, String var2);

void setIntHeader(String var1, int var2);

void addIntHeader(String var1, int var2);
```

- 响应的状态码

```java
int SC_CONTINUE = 100;
int SC_SWITCHING_PROTOCOLS = 101;
int SC_OK = 200;
int SC_CREATED = 201;
int SC_ACCEPTED = 202;
int SC_NON_AUTHORITATIVE_INFORMATION = 203;
int SC_NO_CONTENT = 204;
int SC_RESET_CONTENT = 205;
int SC_PARTIAL_CONTENT = 206;
int SC_MULTIPLE_CHOICES = 300;
int SC_MOVED_PERMANENTLY = 301;
int SC_MOVED_TEMPORARILY = 302;
int SC_FOUND = 302;
int SC_SEE_OTHER = 303;
int SC_NOT_MODIFIED = 304;
int SC_USE_PROXY = 305;
int SC_TEMPORARY_REDIRECT = 307;
int SC_BAD_REQUEST = 400;
int SC_UNAUTHORIZED = 401;
int SC_PAYMENT_REQUIRED = 402;
int SC_FORBIDDEN = 403;
int SC_NOT_FOUND = 404;
int SC_METHOD_NOT_ALLOWED = 405;
int SC_NOT_ACCEPTABLE = 406;
int SC_PROXY_AUTHENTICATION_REQUIRED = 407;
int SC_REQUEST_TIMEOUT = 408;
int SC_CONFLICT = 409;
int SC_GONE = 410;
int SC_LENGTH_REQUIRED = 411;
int SC_PRECONDITION_FAILED = 412;
int SC_REQUEST_ENTITY_TOO_LARGE = 413;
int SC_REQUEST_URI_TOO_LONG = 414;
int SC_UNSUPPORTED_MEDIA_TYPE = 415;
int SC_REQUESTED_RANGE_NOT_SATISFIABLE = 416;
int SC_EXPECTATION_FAILED = 417;
int SC_INTERNAL_SERVER_ERROR = 500;
int SC_NOT_IMPLEMENTED = 501;
int SC_BAD_GATEWAY = 502;
int SC_SERVICE_UNAVAILABLE = 503;
int SC_GATEWAY_TIMEOUT = 504;
int SC_HTTP_VERSION_NOT_SUPPORTED = 505;
```

### 常见应用

1. 向浏览器输出信息
2. 下载文件
    1. 要获取下载文件的路径
    2. 下载的文件名是啥?
    3. 设置想办法让浏览器能够支持下载我们需要的东西
    4. 获取下载文件的输入流
    5. 创建缓冲区
    6. 获取OutputStream对象
    7. 将FileOutputStream流写入到buffer缓冲区
    8. 使用OutputStream将缓冲区中的数据输出到客户端!

3. 验证码
    1. 设置网页自动刷新
    2. 设置验证码
    3. 设置浏览器不缓存
    4. 返回验证码

### 实现重定向

```java
resp.sendRedirect("/img");
```

相当于

```java
resp.setHeader("Location", "/r/img");
resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
```

**面试题:请你聊聊重定向和转发的区别?**

- 相同点
    - 页面都会实现跳转
- 不同点
    - 请求转发的时候，url不会产生变化
    - 重定向时候，url地址栏会发生

## 7、HttpServletRequest

HttpServletRequest代表客户端的请求，用户通过Http协议访问服务器，HITP请求中的所有信息会
被封装到HttpServletRequest，通过这个HttpServletRequest的方法，获得客户端的所有信息

### 请求转发

```java
req.getRequestDispatcher("/success.jsp").forward(req, resp);
```

### 获得前端参数

得到单个

```java
req.getParameter("username")
```

得到多选

```java
req.getParameterValues("hobby")
```

# Cookie、Session

## **cookie**

- 客户端技术  (响应，请求)

```java
Cookie[] cookies = req.getCookies(); // 得到全部的cookie
Cookie cookie = cookies[0];
String name = cookie.getName(); // 得到cookie的name

resp.addCookie(new Cookie("key", "我是Cookie的值")); // 添加一个cookie

cookie.setMaxAge(24 * 60 * 60); // 设置过期时间
```

- cookie**一般会保存在本地的用户目录下appdata;**

- 一个Cookie只能保存一个信息;
- 一个web站点可以给浏览器发送多个cookie,
- 300个cookie浏览器上限
- Cookie大小限制4kb



删除Cookie

- 不设置有效期，关闭浏览器，自动失效
- 设置有效期 0；



## **session**

- 服务器技术，利用这个技术，可以保存用户的会话信息?我们可以把信息或者数据放在Sessidn中

什么是Session:

- 服务器会给每一个用户（浏览器）创建一个Seesion对象;

- 一个Seesion独占一个浏览器，只要浏览器没有关闭，这个Session就存在;

- 用户登录之后，整个网站它都可以访问啊!

- 创建的的时候

    ```java
    Session创建的时候做了什么事情
    resp.addCookie(new Cookie("JSESSIONID", id));
    ```



Session和Cookie的区别：

- Cookie是把用户的数据写给用户的浏览器，浏览器保存（可以保存多个）
- Session把用户的数据写到用户独占Session中，服务器端保存（保存重要的信息，减少服务器资源的浪费)
- 



实现

```java
HttpSession session = req.getSession(); // 得到Session
session.setAttribute("name", "阿偷ProMax"); // 设置
session.getId();  // 得到ID
session.isNew(); // 判断是否为new

// 设置值
String name = (String) session.getAttribute("name");
Person pojo = (Person) session.getAttribute("pojo");

session.removeAttribute("name"); // 移除值
session.invalidate();	// 注销session

```

web.xml

```xml
<session-config>
    <!-- 15分钟后session自动失效 ， 分钟为单位-->
    <session-timeout>15</session-timeout>
</session-config>
```



使用场景:

- 保存一个登录用户的信息
- 购物车信息
- 在整个网站中经常会使用的数据，我们将它保存在Session中



# JavaBean

实体类

JavaBean有特定的写法

- 必须要有一个无参构造
- 属性必须私有化
- 必须有对应的get/set方法;

一般用来和数据库的字段做映射ORM;
ORM : 对象关系映射

- 表--->类
- 字段-->属性
- 行记录---->对象



# Filter

web服务启动成功后，初始化就启动了



java

```java
public class CharacterEncodingFilter implements Filter {


    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {

        req.setCharacterEncoding("utf-8");
        res.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=UTF-8");

        System.out.println("执行前");
        filterChain.doFilter(req, res);     // 让我请求继续走，否则就被拦截
        System.out.println("执行后");

    }

    public void init(FilterConfig filterConfig) throws ServletException {

        System.out.println("👴初始化辣");

    }

    public void destroy() {

        System.out.println("👴被销毁了辣");
    }
}
```

web.xml

```java
<filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class>com.ahhTou.filter.CharacterEncodingFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>CharacterEncodingFilter</filter-name>
    <url-pattern>/servlet/*</url-pattern>
</filter-mapping>
```

# Listener

```java
// 统计网页在线人数 ： 统计session
public class OnlineCountListener implements HttpSessionListener {

    // 创建session监听
    // 一单创建Session就会触发这个事件
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext ctx = se.getSession().getServletContext();
        Integer onlineCount = (Integer) ctx.getAttribute("OnlineCount");

        if (onlineCount == null) {
            onlineCount = 1;
        } else {
            onlineCount = onlineCount + 1;
        }

        ctx.setAttribute("OnlineCount", onlineCount);
    }


}
```

```xml
<listener>
    <listener-class>com.ahhTou.listener.OnlineCountListener</listener-class>
</listener>
```