package U05_String;

public class StringToStringBuilder {

    public static void main(String[] args) {
        // StringBuilder to String
        StringBuilder sb = new StringBuilder();
        sb.append("hello");

        String s = sb.toString();
        System.out.println(s);

        // String to StringBuilder
        String s2 = "hello";
        StringBuilder sb2 = new StringBuilder(s);
        System.out.println(sb2);


    }
}
