package U4_Bean.impl;


public class AccountServiceImpl implements IAccountService {

//    public AccountServiceImpl(String name){
//        System.out.println("对象创建了");
//    }
//
//    public AccountServiceImpl() {
//
//    }

    public void saveAccount() {
        System.out.println("service中的saveAccount方法启动了");
    }

    public void init() {
        System.out.println("对象创建了");
    }

    public void destroy() {
        System.out.println("对象销毁了");
    }
}
