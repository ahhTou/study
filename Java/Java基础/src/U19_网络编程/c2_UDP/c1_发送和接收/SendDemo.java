package U19_网络编程.c2_UDP.c1_发送和接收;

import java.io.IOException;
import java.net.*;

public class SendDemo {
    public static void main(String[] args) throws IOException {

        // 创建对象
        DatagramSocket ds = new DatagramSocket();

        // 创建数据
        byte[] bys = "hello,udp我来了".getBytes();

        DatagramPacket dp = new DatagramPacket(
                bys,                                // 数据
                bys.length,                         // 数据长度 int length = bys.length;
                InetAddress.getByName("ahhTou"),    // 发送的目标 InetAddress address = InetAddress.getByName("ahhTou");
                10086                          // 端口 int port = 10086;
        );

        // 发送数据
        ds.send(dp);

        // 关闭发送端
        ds.close();

        System.out.println("已发送数据，并关闭了客服端");


    }
}
