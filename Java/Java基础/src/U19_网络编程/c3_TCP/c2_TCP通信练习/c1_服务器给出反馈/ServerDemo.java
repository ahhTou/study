package U19_网络编程.c3_TCP.c2_TCP通信练习.c1_服务器给出反馈;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {
    public static void main(String[] args) throws IOException {
        // 创建对象
        ServerSocket ss = new ServerSocket(9999);

        // 监听客户端
        Socket s = ss.accept();

        // 获取输入流
        InputStream is = s.getInputStream();
        byte[] bys = new byte[1024];
        int len = is.read(bys);
        String data = new String(bys, 0, len);
        System.out.println("服务器接收到了" + data);

        // 给出反馈
        OutputStream os = s.getOutputStream();
        os.write("ServerMsg:数据已经收到".getBytes());

        // 释放资源
        ss.close();

    }
}
