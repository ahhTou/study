package U19_网络编程.c1_网络通信要素;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class c1_InetAddress {
    public static void main(String[] args) throws UnknownHostException {
        // 得到主机对象 可以是主机名称，也可以是ip地址
        InetAddress address = InetAddress.getByName("ahhTou");
//        InetAddress address = InetAddress.getByName("192.168.2.105");

        // 得到主机名
        String name = address.getHostName();

        // 得到ip地址
        String ip = address.getHostAddress();

        System.out.println("主机名" + name);
        System.out.println("IP地址" + ip);
    }
}
