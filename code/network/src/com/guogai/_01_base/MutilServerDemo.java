package com.guogai._01_base;

import com.guogai._02_tcp.Server;

import javax.xml.bind.ValidationEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by guo on 2018/1/24.
 * 处理多个客户端
 */
public class MutilServerDemo {
    public static void main(String[] args) {
        ExecutorService ex = Executors.newFixedThreadPool(3);
        try {
            ServerSocket  server = new ServerSocket(8888);
            System.out.println("服务器已启动，正在等待连接");
            while(true) {
              Socket s = server.accept();
                System.out.println(s.getInetAddress().getHostAddress());
                ex.execute(new UserThread(s));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*
 *用来处理客户端请求的线程
 */
class UserThread implements Runnable{
    private  Socket s;
    public UserThread(Socket s) {
        this.s = s;
    }
    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintStream ps = new PrintStream(new BufferedOutputStream(s.getOutputStream()));
            String info = br.readLine();
            System.out.println(info);
            ps.println("Echo " + info);
            ps.flush();
            ps.close();
            br.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}