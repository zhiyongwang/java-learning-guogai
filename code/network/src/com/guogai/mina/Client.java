package com.guogai.mina;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * Created by guo on 2018/1/24.
 */
public class Client {
    public static void main(String[] args) {
        //创建连接
        NioSocketConnector connector = new NioSocketConnector();
        DefaultIoFilterChainBuilder chain = connector.getFilterChain();
        //chain.addLast("mychain",new ProtocolCodecFilter(new TextLineCodecFactory()));
        chain.addLast("objectFilter",new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        connector.setHandler(new MinaClientHandler());
        connector.setConnectTimeoutMillis(4000);
        //连接服务器
        ConnectFuture connect = connector.connect(new InetSocketAddress("127.0.0.1", 8888));
        connect.awaitUninterruptibly(); //等待连接成功
        Scanner input = new Scanner(System.in);
        while(true){
          /*  System.out.println("请输入：");
            String info = input.nextLine();
            //发送消息
            connect.getSession().write(info);*/
          //已对象的方式发送数据
            Message msg = new Message();
            System.out.println("from：");
            msg.setFrom(input.nextLine());
            System.out.println("to:");
            msg.setTo(input.nextLine());
            System.out.println("type:");
            msg.setType(input.nextLine());
            System.out.println("info:");
            msg.setInfo(input.nextLine());
            connect.getSession().write(msg);

        }
        //等待服务器连接关闭，结束长连接。
        //connect.getSession().getCloseFuture().awaitUninterruptibly();
        //释放掉资源
        //connector.dispose();

    }
}
