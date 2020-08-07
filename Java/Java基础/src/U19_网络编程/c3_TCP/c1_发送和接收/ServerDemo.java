package U19_网络编程.c3_TCP.c1_发送和接收;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {
    public static void main(String[] args) throws IOException {
        // 创建服务器Socket对象
        ServerSocket ss = new ServerSocket(9999);

        // 监听要链接到此套接字并接受它
        Socket s = ss.accept();

        // 获取输入流
        InputStream is = s.getInputStream();

        byte[] bys = new byte[1024];
        int len = is.read(bys);
        String data = new String(bys, 0, len);
        System.out.println("数据是：" + data);

        // 释放资源
        s.close();
        ss.close();


    }
}
