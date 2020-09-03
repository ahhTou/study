package com.ahhtou.config;

import com.ahhtou.config.bean.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 *
 */

@SpringBootTest
class ApplicationTests {

    @Autowired
    Person person;

    @Autowired
    ApplicationContext ioc;

    @Test
    public void testHelloService(){
        boolean helloService = ioc.containsBean("helloService");
        System.out.println(helloService);
    }

    @Test
    void contextLoads() {
        System.out.println(person);

    }

}
