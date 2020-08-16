package com.ahhTou.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"classpath:config.properties"})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyEmail {
    String password;
    String sendEmail;
}
