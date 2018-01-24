package com.guogai._01_base;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by guo on 2018/1/24.
 */
public class MutilClientDemo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try {
            //创建一个Socket，指定要连接的服务器
            Socket socket = new Socket("127.0.0.1",8888);
            //获取Socket对象的输入流
            PrintStream ps = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("请输入：");
            String info = input.nextLine();
            ps.println(info);
            ps.flush();
            //读取服务端返回的数据
            info = br.readLine();
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
