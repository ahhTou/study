package com.ahhtou.dao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan(basePackages = {"com.ahhtou.dao.mapper"}) //扫描的mapper
@SpringBootApplication
public class HelloDaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloDaoApplication.class, args);
    }

}
