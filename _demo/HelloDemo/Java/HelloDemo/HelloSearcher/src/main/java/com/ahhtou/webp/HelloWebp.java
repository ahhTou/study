package com.ahhtou.webp;

import lombok.Data;


@Data
public class HelloWebp {

    private static HelloWebpReader reader;
    private static HelloWebpWriter writer;

    public static HelloWebpWriter getWriter() {
        if (writer == null) writer = new HelloWebpWriter();
        return writer;
    }

    public static HelloWebpReader getReader() {
        if (reader == null) reader = new HelloWebpReader();
        return reader;
    }


}
