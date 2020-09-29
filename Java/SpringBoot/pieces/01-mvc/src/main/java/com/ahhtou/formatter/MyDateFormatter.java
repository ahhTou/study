package com.ahhtou.formatter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.format.Formatter;
import org.springframework.format.datetime.DateFormatter;

import java.util.Date;

public class MyDateFormatter {
//    @Bean
//    @ConditionalOnProperty(prefix = "spring.mvc",name = "date-formatter")
//    // 在文件中配置日期格式化规则
//    public Formatter<Date> dateFormatter(){
//        return new DateFormatter(this.mvc)
//    }
}
