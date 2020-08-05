package com.ahhTou.MyDemo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Random;

public class MyIOC {

    static Object object;

    static {
        try {

            System.out.println("自动注入");
            Class<?> aClass = Class.forName("com.ahhTou.MyDemo.Main");
            Object o = aClass.newInstance();
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                Annotation[] annotations = declaredField.getAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof MyAnnotation) {
                        declaredField.set(o, createVerificationCode());
                    }
                }
            }
            object = o;


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String createVerificationCode() {
        Random random = new Random(new Date().getTime());
        String code = "";
        for (int i = 0; i < 6; i++) {
            int i1 = random.nextInt(10);
            code = code.concat(String.valueOf(i1));
        }
        return code;
    }

    public static Object getBean() {
        return object;
    }

}
