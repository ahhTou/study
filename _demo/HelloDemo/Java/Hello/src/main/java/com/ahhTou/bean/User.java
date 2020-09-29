package com.ahhTou.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {

    private Integer id;
    private String email;
    private String username;
    private String password;
    private String createTime;
    private String updateTime;

    private String code;
    private String token;

}
