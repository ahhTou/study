package U14_常用Api;

import java.util.Arrays;

public class c6_split {
    public static void main(String[] args) {
        String s = "91 ,27 sdfds46fsd 38fsdfsd 50";

        String[] strArray = s.split("(\\D)+");

        System.out.println(Arrays.toString(strArray));

    }
}
