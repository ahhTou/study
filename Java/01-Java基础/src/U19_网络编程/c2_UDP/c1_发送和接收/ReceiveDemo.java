package U19_网络编程.c2_UDP.c1_发送和接收;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveDemo {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(10086); // 端口

        byte[] bys = new byte[1024];

        // 创建数据包，用于接收数据
        DatagramPacket dp = new DatagramPacket(bys, bys.length);

        // 接收
        System.out.println("等待接收数据");
        ds.receive(dp);

        // 解析
        System.out.println("数据是" +
                new String(
                        dp.getData(),  // byte[] dataS = dp.getData();
                        0,
                        dp.getLength() //int len = dp.getLength();
                ));

        // 关闭
        ds.close();

    }
}
