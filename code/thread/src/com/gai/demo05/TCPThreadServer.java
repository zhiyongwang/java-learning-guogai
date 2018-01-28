package com.gai.demo05;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by guo on 2018/1/28.
 */
public class TCPThreadServer  {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8888);
        while (true) {
            //获取一个客户端，必须开启新的线程
            Socket socket = ss.accept();
            new Thread(new Upload(socket)).start();
        }
    }
}
