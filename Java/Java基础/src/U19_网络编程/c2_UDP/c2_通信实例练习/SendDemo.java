package U19_网络编程.c2_UDP.c2_通信实例练习;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendDemo {
    public static void main(String[] args) throws IOException {
        // 创建Socket对象
        DatagramSocket ds = new DatagramSocket();

        // 自己封装键盘录入数据
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        System.in
                )
        );

        String line;

        while ((line = br.readLine()) != null) {
            System.out.println("发送的数据为：" + line);
            if ("886".equals(line)) {
                break;
            }

            //创建数据
            byte[] bys = line.getBytes();
            DatagramPacket dp = new DatagramPacket(
                    bys,
                    bys.length,
                    InetAddress.getByName("ahhTou"),
                    12345
            );
            ds.send(dp);
        }

        //关闭发送端
        ds.close();

    }
}
