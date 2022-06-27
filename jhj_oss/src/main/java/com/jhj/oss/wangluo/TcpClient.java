package com.jhj.oss.wangluo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class TcpClient {

    public static void main(String[] args) throws Exception {
        InetAddress byName = InetAddress.getByName("127.0.0.1");
        int port = 9999;
        Socket socket = new Socket(byName, port);
        OutputStream os = socket.getOutputStream();
        os.write("你好".getBytes(StandardCharsets.UTF_8));
    }
}
