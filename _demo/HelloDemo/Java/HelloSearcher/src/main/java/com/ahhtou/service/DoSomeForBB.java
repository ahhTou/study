package com.ahhtou.service;

import com.ahhtou.zac.DoAddSuffix;

import java.awt.image.BufferedImage;
import java.util.Map;

public interface DoSomeForBB extends DoSome<Map<String, BufferedImage>, Map<String, BufferedImage>> {


    default DoCut doCut() {
        return new DoCut().doSome(getThings());
    }

    default DoZip doZip() {
        return new DoZip().doSome(getThings());
    }

    default DoWrite doWrite() {
        return new DoWrite().doSome(getThings());
    }

    default DoAddSuffix doAddSuffix() {
        return new DoAddSuffix().doSome(getThings());
    }


}
