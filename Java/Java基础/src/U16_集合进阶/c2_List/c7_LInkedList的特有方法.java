package U16_集合进阶.c2_List;

import java.util.LinkedList;

public class c7_LInkedList的特有方法 {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<String>();

        linkedList.add("hello");
        linkedList.add("world");
        linkedList.add("Java");

        // 头部添加
        linkedList.addFirst("JavaEE");

        // 尾巴添加
        linkedList.addLast("JavaSE");

        System.out.println("getFirst:" + linkedList.getFirst());
        System.out.println("getLast:" + linkedList.getLast());

        System.out.println(linkedList);

        linkedList.removeLast();
        linkedList.removeFirst();

        System.out.println(linkedList);
    }
}
