package com.ahhtou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication // 标准这个类是一个Springboot的应用 -> 启动类下的所有资源被导入
public class Springboot01Application {

    public static void main(String[] args) {

        // 将Springboot启动
        SpringApplication.run(Springboot01Application.class, args);
    }

}