package U19_网络编程.c3_TCP.c2_TCP通信练习.c6_多线程实现文件上传;

import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable {
    private Socket s;

    public ServerThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            s.getInputStream()
                    )
            );


            int cout = 0;
            File file = new File("./src/U19_网络编程/c3_TCP/c2_TCP通信练习/c6_多线程实现文件上传/Server" + cout + ".txt");
            while (file.exists()) {
                cout++;
            }
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(file)
            );

            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
                bw.flush();
            }

            BufferedWriter bwServer = new BufferedWriter(
                    new OutputStreamWriter(
                            s.getOutputStream()
                    )
            );
            bwServer.write("文件上传成功");
            bwServer.newLine();
            bwServer.flush();

            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
