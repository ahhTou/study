package com.ahhtou.doSome;

import com.ahhtou.doSome.DoSomeImpl.DoTransToBuff;
import com.ahhtou.doSome.DoSomeImpl.DoZacFilter;

import java.util.Set;

public interface DoSomeForSS extends DoSome<Set<String>, Set<String>> {

    default DoTransToBuff doTrans() {
        return new DoTransToBuff().doSome(getThings());
    }

    default DoZacFilter doFilter() {
        return new DoZacFilter().doSome(getThings());
    }

}
