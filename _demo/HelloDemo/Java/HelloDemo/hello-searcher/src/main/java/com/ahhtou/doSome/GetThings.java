package com.ahhtou.doSome;

import com.ahhtou.doSome.DoSomeImpl.DoSearch;
import com.ahhtou.logger.HelloLogger;
import com.ahhtou.utils.HelloUtils;
import com.ahhtou.utils.Properties;
import com.ahhtou.utils.ZipAndCutProperties;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Set;

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

    public static <R> GetThings<R> create(R start_args) {
        GetThings<R> returnThings = new GetThings<>();
        returnThings.setValue(start_args);
        return returnThings;

    }

    public DoSearch doSearch() {

        GetThings<Set<String>> getThings = new GetThings<>();
        BeanUtils.copyProperties(this, getThings);

        return new DoSearch().doSome(getThings);
    }

}
