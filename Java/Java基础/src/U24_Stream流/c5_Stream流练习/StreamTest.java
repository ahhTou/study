package U24_Stream流.c5_Stream流练习;

import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        ArrayList<String> manlist = new ArrayList<>();
        manlist.add("周润发");
        manlist.add("成龙");
        manlist.add("刘德华");
        manlist.add("吴京");
        manlist.add("周星驰");
        manlist.add("李连杰");

        ArrayList<String> womanlist = new ArrayList<>();
        womanlist.add("林青霞");
        womanlist.add("张曼玉");
        womanlist.add("王祖贤");
        womanlist.add("柳岩");
        womanlist.add("张敏");
        womanlist.add("张无忌");

        Stream<String> manStream = manlist.stream()
                .filter(s -> s.length() == 3)
                .limit(3);

        Stream<String> womanStream = womanlist.stream()
                .filter(s -> s.length() == 3)
                .limit(3);

        Stream<String> stream = Stream.concat(manStream, womanStream);

        stream.map(Actor::new).forEach(p -> System.out.println(p.getName()));

    }
}
