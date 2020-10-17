package com.ahhtou.doSome.DoSomeImpl;

import org.springframework.beans.BeanUtils;

import java.util.Set;

public class DoStart {

    private final GetThings<Set<String>> getThings;

    public static <R> GetThings<R> cGetThings(R start_args) {
        GetThings<R> returnThings = new GetThings<>();
        returnThings.setValue(start_args);
        return returnThings;

    }

    public DoStart(GetThings<Set<String>> getThings) {
        GetThings<Set<String>> newGetThings = new GetThings<>();
        BeanUtils.copyProperties(getThings, newGetThings);
        this.getThings = newGetThings;
    }

    public DoSearch doSearch() {
        return new DoSearch().doSome(getThings);
    }
}
