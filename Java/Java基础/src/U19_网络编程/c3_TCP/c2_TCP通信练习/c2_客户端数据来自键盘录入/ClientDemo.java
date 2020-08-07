package U19_网络编程.c3_TCP.c2_TCP通信练习.c2_客户端数据来自键盘录入;

import java.io.*;
import java.net.Socket;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("ahhTou", 9999);

        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        System.in
                )
        );
        BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        s.getOutputStream()
                )
        );

        String line;

        while ((line = br.readLine()) != null) {
            if ("886".equals(line)) {
                break;
            }
            bw.write(line);
            bw.newLine();
            bw.flush();
        }

        bw.close();

    }
}
