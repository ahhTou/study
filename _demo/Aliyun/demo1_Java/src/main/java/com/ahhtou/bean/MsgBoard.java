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
public class MsgBoard implements Serializable {

    String sendName;
    String content;
    Date sendTime;

}
