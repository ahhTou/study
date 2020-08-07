package U05_String;

public class StringBuilderDemo2 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        StringBuilder sb2 = sb.append("hello");

        System.out.println("sb:" + sb);
        System.out.println("sb2:" + sb2);

        sb.append("123");
        sb.append("123");
        sb.append("123");
        sb.append("123");
        System.out.println("sb:" + sb);

        sb.reverse();
        System.out.println("sb反转：" + sb);
    }
}
