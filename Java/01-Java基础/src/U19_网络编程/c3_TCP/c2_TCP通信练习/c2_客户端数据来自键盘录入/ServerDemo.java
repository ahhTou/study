package U19_网络编程.c3_TCP.c2_TCP通信练习.c2_客户端数据来自键盘录入;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {
    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(9999);

        Socket s = ss.accept();

        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        s.getInputStream()
                )
        );
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("服务器接收到" + line);
        }

        ss.close();


    }
}
