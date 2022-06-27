package com.jhj.oss.wangluo;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket();
        Socket socket = serverSocket.accept();
        InputStream is = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        while ((len=is.read(bytes))!=-1){
            String msg = new String(bytes,0 , len);
            System.out.println(msg);
        }
    }
}
