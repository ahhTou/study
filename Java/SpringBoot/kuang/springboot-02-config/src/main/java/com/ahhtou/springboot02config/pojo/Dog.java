package com.ahhtou.springboot02config.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dog {

    @Value("旺财")
    private String name;

    @Value("3")
    private Integer age;

}
