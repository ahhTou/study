package com.ahhtou.logger;


import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloLogger {

    private final String project;
    private final String name;
    private String doWhat;

    public HelloLogger(String project, String name) {
        this.project = project;
        this.name = name;
    }

    public static HelloLogger create(String project, String name) {
        return new HelloLogger(project, name);
    }


    public void log(String doWhat) {
        logToColor(logTemplate(doWhat), HelloLogColor.GREY);
    }
    public void logWhite(String doWhat) {
        logToColor(logTemplate(doWhat), HelloLogColor.WHITE);
    }
    public void logRed(String doWhat) {
        logToColor(logTemplate(doWhat), HelloLogColor.RED);
    }
    public void logGreen(String doWhat) {
        logToColor(logTemplate(doWhat), HelloLogColor.GREEN);
    }
    public void logYellow(String doWhat) {
        logToColor(logTemplate(doWhat), HelloLogColor.YELLOW);
    }
    public void logBlue(String doWhat) {
        logToColor(logTemplate(doWhat), HelloLogColor.BLUE);
    }
    public void logPurple(String doWhat) {
        logToColor(logTemplate(doWhat), HelloLogColor.PURPLE);
    }
    public void logGB(String doWhat) {
        logToColor(logTemplate(doWhat), HelloLogColor.GREEN_BLUE);
    }
    public void logGray(String doWhat) {
        logToColor(logTemplate(doWhat), HelloLogColor.GREY);
    }


    public String logTemplate(String doWhat) {
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
        SimpleDateFormat format = new SimpleDateFormat(strDateFormat);
        return format.format(date) + "  "
//                + "[ " + Thread.currentThread().getName() + " ] "
                + this.project + "::" + this.name + " -> " + doWhat;

    }

    private void logToColor(String say, HelloLogColor color) {
        String prefix1 = "\033[";
        String prefix2 = ";1m";

        String suffix = "\033[0m";
        System.out.println(prefix1 + color.getColor() + prefix2 + say + suffix);
    }


}
