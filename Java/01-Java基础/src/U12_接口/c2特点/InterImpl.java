package U12_接口.c2特点;

// 等价
// public class InterImpl extends Object implements Inter

public class InterImpl implements Inter {

//    public InterImpl() {
//        super(); // 用的 Object() 的构造方法
//    }

    @Override
    public void show() {
        System.out.println("111");
    }
}
