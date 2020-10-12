package com.ahhtou.service;

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
