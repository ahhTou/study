package U19_网络编程.c3_TCP.c2_TCP通信练习.c1_服务器给出反馈;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        // 创建对象
        Socket s = new Socket("ahhTou", 9999);

        // 获取输出流，写数据
        OutputStream os = s.getOutputStream();
        os.write("hello,tcp, 我来了".getBytes());

        // 接收服务器反馈
        InputStream is = s.getInputStream();
        byte[] bys = new byte[1024];
        int len = is.read(bys);
        System.out.println("客户端接收到的反馈为: " +
                new String(bys, 0, len));

        // 释放资源
        s.close(); // s被释放时，is和os都会被释放


    }
}
