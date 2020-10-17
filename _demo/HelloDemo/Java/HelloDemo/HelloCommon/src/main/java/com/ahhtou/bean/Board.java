package com.ahhtou.bean;


import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Board implements Serializable {

    private String sendName;
    private String content;
    private Date sendTime;

}
