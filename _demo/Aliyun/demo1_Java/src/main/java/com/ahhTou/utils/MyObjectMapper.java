package com.ahhTou.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;

@Component
public class MyObjectMapper {

    @Resource
    ObjectMapper objectMapper;

    public <T> String obj2String(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);

        } catch (IOException e) {
            return null;
        }
    }

    public <T> T string2Obj(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            System.out.println("1");
            return null;
        }
        try {
            System.out.println("2");
            return objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            System.out.println("3");
            e.printStackTrace();
            return null;
        }
    }

}
