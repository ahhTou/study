package com.ahhtou.doSome.DoSomeImpl;

import com.ahhtou.logger.HelloLogger;
import com.ahhtou.utils.HelloUtils;
import com.ahhtou.utils.Properties;
import com.ahhtou.utils.ZipAndCutProperties;
import lombok.Data;

@Data
public class GetThings<T> {

    private T value;

    private HelloLogger helloLogger;

    private Properties properties;

    private ZipAndCutProperties zacProperties;

    public HelloUtils getHelloUtils() {
        return new HelloUtils(properties);
    }


    public <R> GetThings<R> set(R newValue) {

        GetThings<?> getThings = this;

        @SuppressWarnings("unchecked")
        GetThings<R> newThings = (GetThings<R>) getThings;

        newThings.setValue(newValue);


        return newThings;
    }
}
