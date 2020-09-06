# SpringBoot集成

pom依赖

```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>
```



简单启动

```
@Configuration
@EnableSwagger2//开启Swagger 2
public class SwaggerConf {
    
}
```



# 配置

## Swagger的bean实例 Docket

```java
@Configuration
@EnableSwagger2//开启Swagger 2
public class SwaggerConf {

    // 配置了Swagger 的 Docket 的 bean实例
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2);
    }

}
```



## 配置swagger的ui信息

```java
@Configuration
@EnableSwagger2//开启Swagger 2
public class SwaggerConf {

    // 配置了Swagger 的 Docket 的 bean实例
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());
    }

    // 配置Swagger 信息 = apiInfo
    private ApiInfo apiInfo() {
        return new ApiInfo("ahhTou Api 接口文档",
                "阿偷",
                "v1.0",
                "null",
                new Contact("", "", ""),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }
}
```



## 扫描接口包

```java
@Bean
public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            // 配置要扫描接口的方式
       .apis(RequestHandlerSelectors.basePackage("com.ahhtou.swagger.controller"))
            .build();
}
```

```java
// 指定包
RequestHandlerSelectors.basePackage("com.ahhtou.swagger.controller");
// 扫描全部
RequestHandlerSelectors.any;
// 指定注解
RequestHandlerSelectors.withClassAnnotation(RestController.class);
RequestHandlerSelectors.withMethodAnnotation(RestController.class)
```

## 过滤路径

```java
@Bean
public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .paths(PathSelectors.ant("/ahhTou/**")) // 只扫描这个路径下的请求
            .build();
}
```



## 配置是否启动Swagger

```java
@Bean
public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .paths(PathSelectors.ant("/ahhTou/**"))
            .build().enable(false);
}
```





## 分组

```java
@Bean
public Docket docket1() {
    return new Docket(DocumentationType.SWAGGER_2)
            .groupName("Hello1");
}

@Bean
public Docket docket2() {
    return new Docket(DocumentationType.SWAGGER_2)
            .groupName("Hello2");
}

@Bean
public Docket docket3() {
    return new Docket(DocumentationType.SWAGGER_2)
            .groupName("Hello3");
}
```



# 注释



返回值添加到model中

```java
@GetMapping(path = {"/user"})
public User user() {
    User user = new User();
    user.pwd = "123";
    user.username = "ahhTou";

    return user;
}
```



手动注释

实体类

```java
@ApiModel("用户实体类")
public class User {

    @ApiModelProperty("用户名")
    public String username;
    @ApiModelProperty("密码")
    public String pwd;
}
```

# 测试