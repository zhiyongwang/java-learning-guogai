package com.guogai._01_base;

import java.io.*;
import java.net.Socket;

/**
 * Created by guo on 2018/1/24.
 */
public class EchoClientDemo {
    public static void main(String[] args) {
        try {
            //创建一个Socket，指定要连接的服务器
            Socket socket = new Socket("127.0.0.1",8888);
            //获取Socket对象的输入流
            PrintStream ps = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ps.println("每一次的努力，你会获取一点知识。坚持下去你会取得更多");
            ps.flush();
            //读取服务端返回的数据
            String info = br.readLine();
            System.out.println(info);

            //关闭相应的流和Socket流
            br.close();
            ps.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
