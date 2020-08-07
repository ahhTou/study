package U19_网络编程.c3_TCP.c1_发送和接收;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        // 创建客户端socket对象
//        Socket s = new Socket(
//                InetAddress.getByName("ahhTou"), // 也可以自己接给String字符串
//                10000
//        );

        Socket s = new Socket("ahhTOu", 9999);

        // 获取输出流，写数据
        OutputStream os = s.getOutputStream();
        os.write("hello,tcp,我来了".getBytes());

        // 释放资源
        s.close();

    }
}
