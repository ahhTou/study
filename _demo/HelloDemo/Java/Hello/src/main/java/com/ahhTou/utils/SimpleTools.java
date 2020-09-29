package com.ahhTou.utils;


import com.ahhTou.bean.User;
import com.ahhTou.utils.var.CommonConstant;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class SimpleTools {

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

    /* 得到加密的密码 */
    public static String getPwdByMd5(User user) {
        return new SimpleHash("md5", user.getPassword(), CommonConstant.MD5_SALT, 1024).toHex();

    }


}
