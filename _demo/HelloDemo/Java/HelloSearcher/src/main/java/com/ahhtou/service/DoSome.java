package com.ahhtou.service;


import com.ahhtou.logger.HelloLogger;

public interface DoSome<T, R> {
    GetThings<R> getThings();

    default HelloLogger log() {
        return getThings().getHelloLogger();
    }

    default R getResult() {
        return getThings().getValue();
    }

    DoSome<T, R> doSome(GetThings<T> getThings);

}
