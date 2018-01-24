package com.guogai.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by guo on 2018/1/24.
 * 服务端
 */
public class Server {
    public static void main(String[] args) {
        //保存客户端处理的线程
        Vector<UserThread> vector = new Vector<>();
        //创建一个线程池
        ExecutorService ex = Executors.newFixedThreadPool(5);

        //创建服务端的Socket
        try {
            ServerSocket server = new ServerSocket(8888);
            System.out.println("服务端已启动，正在等待连接...");
            while (true) {
                Socket socket = server.accept();
                UserThread user = new UserThread(socket, vector);
                ex.execute(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

/**
 * 客户端处理的线程
 */
class UserThread implements Runnable {
    private String name; //客户端的用户名称（标识号唯一）
    private Socket socket;
    private Vector<UserThread> vector;  //客户端处理线程的集合
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private boolean flag = true;

    public UserThread(Socket socket, Vector<UserThread> vector) {
        this.socket = socket;
        this.vector = vector;
        vector.add(this);
    }

    @Override
    public void run() {
        try{
            System.out.println("客户端"+socket.getInetAddress().getHostAddress()+"已连接");
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());

            while (flag){
                //读取消息对象
                Message msg  = (Message)ois.readObject();
                int type = msg.getType();
                switch (type){
                    case MessageType.TYPE_SEND:
                        String to = msg.getTo();
                        UserThread ut;
                        int size = vector.size();
                        for (int i = 0; i < size; i++) {
                            ut = vector.get(i);
                            if (to.equals(ut.name) && ut!=this){
                                ut.oos.writeObject(msg);
                                break;
                            }
                        }
                        break;
                    case MessageType.TYPE_LOGIN:
                        name = msg.getFrom();
                        msg.setInfo("欢迎你：");
                        oos.writeObject(msg);
                        break;
                }
            }
            ois.close();
            oos.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
