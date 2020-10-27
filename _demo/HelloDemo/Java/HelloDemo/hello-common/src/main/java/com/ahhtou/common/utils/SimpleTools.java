package com.ahhtou.common.utils;


import com.ahhtou.common.pojo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static class UrlUtils {

        // 得到file对象文件后缀
        public static String getSuffixName(String url) {

            String reg = ".+(\\\\|/).+(\\..*)$";

            Pattern p = Pattern.compile(reg);
            Matcher m = p.matcher(url);

            if (!m.find()) throw new RuntimeException("没有匹配到后缀");

            return m.group(2);
        }

        // 得到file对象文件 无后缀
        public static String getFileNameNoSuffix(String url) {
            String reg = "\\..*$";

            return url.replaceAll(reg, "");
        }

    }
}
