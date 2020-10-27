package com.ahhtou.doSome;

import com.ahhtou.doSome.DoSomeImpl.DoCut;
import com.ahhtou.doSome.DoSomeImpl.DoZip;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Set;

public interface DoSomeForSB extends DoSome<Set<String>, Map<String, BufferedImage>> {

    default DoCut doCut() {
        return new DoCut().doSome(getThings());
    }

    default DoZip doZip() {
        return new DoZip().doSome(getThings());
    }

}
