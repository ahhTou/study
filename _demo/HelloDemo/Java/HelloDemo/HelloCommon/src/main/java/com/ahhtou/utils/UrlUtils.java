package com.ahhtou.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlUtils {

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
