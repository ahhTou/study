package U14_常用Api.c5_Integer包装类;

public class c4_自动装箱和拆箱 {
    public static void main(String[] args) {
        // 1.装箱
        Integer integer = Integer.valueOf(100); // 手动装箱
        Integer li = 100;  // 自动装箱

        // 2.拆箱
        li = li.intValue() + 200;
        System.out.println(li);
        li += 200; //自动拆箱

        Integer iii = null;
        if (iii!=null){
            iii += 300;
        }
    }
}
