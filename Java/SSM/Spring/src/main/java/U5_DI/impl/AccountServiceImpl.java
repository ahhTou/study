package U5_DI.impl;


import U4_Bean.impl.IAccountService;

import java.util.Date;

public class AccountServiceImpl implements IAccountService {

    // 经常变换的数据，并不适用于注入的方式
    private String name;
    private Integer age;
    private Date birthday;

    public AccountServiceImpl(String name, Integer age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public void saveAccount() {

        System.out.println("service中的saveAccount方法启动了");
        System.out.println(name + ',' + age + ',' + birthday);
    }

}
