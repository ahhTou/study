package com.ahhTou.single;

import java.lang.reflect.Constructor;
import java.util.concurrent.TimeUnit;

// 枚举 是 什么 ？ 本身也是一个class类
public enum EnumSingle {

    INSTANCE(1);

    EnumSingle(int i) {
    }

    public EnumSingle getInstance() {
        return INSTANCE;
    }

}

class Test {
    public static void main(String[] args) throws Exception {
        EnumSingle instance1 = EnumSingle.INSTANCE;
        System.out.println(EnumSingle.INSTANCE);

/*        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        EnumSingle instance2 = declaredConstructor.newInstance();
        System.out.println(instance2);*/

    }
}
