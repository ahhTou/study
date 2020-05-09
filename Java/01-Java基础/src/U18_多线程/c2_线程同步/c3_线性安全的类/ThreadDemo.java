package U18_多线程.c2_线程同步.c3_线性安全的类;

import java.lang.reflect.Array;
import java.util.*;

public class ThreadDemo {
    public static void main(String[] args) {

        StringBuffer sb = new StringBuffer(); // 同步安全
        StringBuilder sb2 = new StringBuilder();

        Vector<String> v = new Vector<>(); // 同步安全, 被替代，多线程中不推荐使用
        ArrayList<String> array = new ArrayList<>();

        Hashtable<String, String> ht = new Hashtable<>(); // 同步安全, 被替代，多线程中不推荐使用
        HashMap<String, String> hm = new HashMap<>();

        // 线性安全类
        List<String> list = Collections.synchronizedList(
                new ArrayList<String>()
        );

        Map<String, String> map = Collections.synchronizedMap(
                new HashMap<String, String>()
        );

    }
}
