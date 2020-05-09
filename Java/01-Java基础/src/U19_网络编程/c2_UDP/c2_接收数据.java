package U19_网络编程.c2_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class c2_接收数据 {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(10086);

        byte[] bys = new byte[1024];

        // 创建数据包，用于接收数据
        DatagramPacket dp = new DatagramPacket(bys, bys.length);

        // 接收
        System.out.println("等待接收数据");
        ds.receive(dp);

        // 解析
        byte[] datas = dp.getData();
        String dataString = new String(datas);
        System.out.println("数据是：" + dataString);

        // 关闭
        ds.close();

    }
}
