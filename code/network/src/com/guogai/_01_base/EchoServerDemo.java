package com.guogai._01_base;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by guo on 2018/1/24.
 */
public class EchoServerDemo {
    public static void main(String[] args) {
        try {
            //创建一个服务端的Socket（1024-65535）
            ServerSocket server = new ServerSocket(8888);
            System.out.println("服务器已启动 ，正在等待客户端连接");
            //等待客户端的连接，造成阻塞。如果有客户端连接成功，立即返回一个Socket对象
            Socket socket = server.accept();
            System.out.println("连接成功的客户端" + socket.getInetAddress().getHostAddress());
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //通过输入流读取网络数据,如果没有可读的消息会造成阻塞。
            String info = br.readLine();
            System.out.println(info);
            //获取输出流，向客户端返回信息
            PrintStream ps = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
            ps.println("Echo :" + info);
            ps.flush();
            //关闭相应的流
            ps.close();
            br.close();
            socket.close();
            server.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
