package com.ahhtou.service;

import com.ahhtou.logger.HelloLogger;
import com.ahhtou.var.Properties;

import java.util.Set;

public class DoStart {

    private final GetThings<Set<String>> getThings;

    public static <R> GetThings<R> cGetThings(R start_args) {
        GetThings<R> returnThings = new GetThings<>();
        returnThings.setValue(start_args);
        return returnThings;

    }

    public DoStart(GetThings<Set<String>> getThings) {
        this.getThings = getThings;
    }

    public DoSearch doSearch() {
        return new DoSearch().doSome(getThings);
    }
}
