package U19_网络编程.c2_UDP.c2_通信实例练习;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveDemo {
    public static void main(String[] args) throws IOException {

        // 创建接收端的对象
        DatagramSocket ds = new DatagramSocket(12345);

        System.out.println("开始接收信息");

        while (true) {
            // 创建接收数据包
            byte[] bys = new byte[1024];
            DatagramPacket dp = new DatagramPacket(
                    bys,
                    bys.length
            );

            // 接收数据包
            ds.receive(dp);

            // 解析数据包
            System.out.println("数据是" + new String(
                    dp.getData(), 0, dp.getLength()
            ));
        }

//        ds.close();

    }
}
