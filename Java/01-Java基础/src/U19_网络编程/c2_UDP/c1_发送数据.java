package U19_网络编程.c2_UDP;

// 不可靠

import java.io.IOException;
import java.net.*;

public class c1_发送数据 {
    public static void main(String[] args) throws IOException {

        // 创建对象
        DatagramSocket ds = new DatagramSocket();

        // 创建数据
        byte[] bys = "hello,udp我来了".getBytes();
//        int length = bys.length;
//        InetAddress address = InetAddress.getByName("ahhTou");
//        int port = 10086;
        DatagramPacket dp = new DatagramPacket(
                bys,
                bys.length,
                InetAddress.getByName("ahhTou"),
                10086
        );

        // 发送数据
        ds.send(dp);


        // 关闭发送端
        ds.close();


    }
}
