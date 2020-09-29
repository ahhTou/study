package com.ahhTou.utils.img.webp;

import lombok.Data;


@Data
public class MyWebp {

    MyWebpReader reader;
    MyWebpWriter writer;

    public MyWebpWriter getWriter() {
        if (writer == null) writer = new MyWebpWriter();
        return writer;
    }

    public MyWebpReader getReader() {
        if (reader == null) reader = new MyWebpReader();
        return reader;
    }


}
