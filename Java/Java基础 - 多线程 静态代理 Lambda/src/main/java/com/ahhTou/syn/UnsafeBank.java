package com.ahhTou.syn;

// 不安全的取钱
// 两个人去银行取钱，账户
public class UnsafeBank {

    public static void main(String[] args) {
        Account account = new Account(100, "结婚基金");

        Drawing you = new Drawing(account, 50, "你");
        Drawing girlFriend = new Drawing(account, 100, "女朋友");

        you.start();
        girlFriend.start();
    }

}

// 账户
class Account {

    int money;
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

// 银行 ： 模拟取款
class Drawing extends Thread {
    final Account account; // 账户
    int drawingMoney; // 取了多少钱
    int nowMoney;   // 现在手里有多钱

    public Drawing(Account account, int drawing, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawing;
    }

    @Override
    public void run() {

        // 锁的对象是改变的对象
        synchronized (account) {
            // 判断有没有钱
            if (account.money - drawingMoney < 0) {
                System.out.println(Thread.currentThread().getName() + "钱不够，取不了");
                return;
            }

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            account.money = account.money - drawingMoney; // 卡里的余额

            nowMoney = nowMoney + drawingMoney; // 你手里的钱

            System.out.println(account.name + " 的余额为 ：" + account.money);

            System.out.println(this.getName() + "手里的钱：" + nowMoney);
        }
    }
}
