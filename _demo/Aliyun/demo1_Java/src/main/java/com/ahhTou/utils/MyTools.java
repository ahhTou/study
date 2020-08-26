package com.ahhTou.utils;


import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class MyTools {

    /* 创建一个验证码 */
    public static String createVerificationCode() {
        Random random = new Random(new Date().getTime());
        String code = "";
        for (int i = 0; i < 6; i++) {
            int i1 = random.nextInt(10);
            code = code.concat(String.valueOf(i1));
        }
        return code;
    }


}
