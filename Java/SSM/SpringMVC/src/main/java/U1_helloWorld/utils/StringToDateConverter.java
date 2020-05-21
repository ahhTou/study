package U1_helloWorld.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {

    /**
     *
     * @param s 传入来的字符串
     * @return
     */
    @Override
    public Date convert(String s) {
        if (s == null){
            throw new RuntimeException("请你传入数据");
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 把日期转化为字符串
            return df.parse(s);
        } catch (ParseException e) {
            throw new RuntimeException("数据类型转换出现异常");
        }
    }
}
