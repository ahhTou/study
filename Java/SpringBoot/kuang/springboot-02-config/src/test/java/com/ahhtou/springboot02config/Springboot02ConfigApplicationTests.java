package com.ahhtou.springboot02config;

import com.ahhtou.springboot02config.pojo.Dog;
import com.ahhtou.springboot02config.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class Springboot02ConfigApplicationTests {


    @Resource
    Dog dog;

    @Resource
    Person person;

    @Test
    void contextLoads() {

        System.out.println(dog);

        System.out.println(person);

    }

}
