package com.ahhtou.logger;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class HelloLogger {

    private String project;
    private String name;

    private String doWhat;

    public HelloLogger(String project, String name) {
        this.project = project;
        this.name = name;
    }

    public static HelloLogger create(String project, String name) {
        return new HelloLogger(project, name);
    }


    public void log(String doWhat) {
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
        SimpleDateFormat format = new SimpleDateFormat(strDateFormat);
        logColor("" + format.format(date) + "  "
                + "[ " + Thread.currentThread().getName() + " ] "
                + this.project + "::" + this.name + " -> " + doWhat
        );
    }

    private void logColor(String say) {
        System.out.println("\033[31;1m" + say + "\033[0m");
    }

    public static void logRed(String say) {
        System.out.println("\033[31;1m" + say + "\033[0m");
    }

}
