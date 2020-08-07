package U16_集合进阶.c4_泛型.c4_泛型接口;

public class GenericImpl<T> implements Generic<T> {
    @Override
    public void show(T t) {
        System.out.println(t);
    }
}
