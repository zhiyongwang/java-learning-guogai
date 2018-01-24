package com.guogai.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by guo on 2018/1/24.
 */
public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //创建单例线程池
        ExecutorService ex  = Executors.newSingleThreadExecutor();
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            System.out.println("服务端连接成功");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //像服务端发送信息
            System.out.println("请输入名称：");
            String name = scanner.nextLine();
            Message msg = new Message(name, null, MessageType.TYPE_LOGIN, null);
            oos.writeObject(msg);
            msg = (Message) ois.readObject();
            System.out.println(msg.getInfo() + msg.getFrom());

            //启动读取消息的线程
            ex.execute(new ReadInfoThread(ois));

            //使用主线程来实现发送消息
            boolean flag = true;
            while(flag) {
                msg = new Message();
                System.out.println("To:");
                msg.setTo(scanner.nextLine());
                msg.setFrom(name);
                msg.setType(MessageType.TYPE_SEND);
                System.out.println("info:");
                msg.setInfo(scanner.nextLine());
                oos.writeObject(msg);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 读取消息的线程
 */
class ReadInfoThread implements Runnable {
    private ObjectInputStream in;
    private boolean flag = true;
    public ReadInfoThread(ObjectInputStream in) {
        this.in = in;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            while (flag) {
                Message message = (Message) in.readObject();
                System.out.println("[" + message.getFrom() + "对我说：" + message.getInfo());
            }
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}