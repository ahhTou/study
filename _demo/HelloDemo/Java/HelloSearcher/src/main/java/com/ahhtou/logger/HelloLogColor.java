package com.ahhtou.logger;

public enum HelloLogColor {
    WHITE("30"),
    RED("31"),
    GREEN("32"),
    YELLOW("33"),
    BLUE("34"),
    PURPLE("35"),
    GREEN_BLUE("36"),
    GREY("37")
    ;

    private final String value;

    HelloLogColor(String value) {
        this.value = value;
    }

    public String getColor() {
        return value;
    }
}
