package U5_DI.impl;


import U4_Bean.impl.IAccountService;

import java.util.Date;

public class AccountServiceImpl2 implements IAccountService {
    private String name;
    private Integer age;
    private Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    // 经常变换的数据，并不适用于注入的方式




    public void saveAccount() {

        System.out.println("service中的saveAccount方法启动了");
        System.out.println(name + ',' + age + ',' + birthday);
    }

}
