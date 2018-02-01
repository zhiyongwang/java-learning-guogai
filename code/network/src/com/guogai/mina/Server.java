package com.guogai.mina;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by guo on 2018/1/24.
 * 使用mina框架实现一个服务端程序
 */
public class Server {
    public static void main(String[] args) {
        //创建一个非阻塞的Server端Socket nio
        SocketAcceptor acceptor = new NioSocketAcceptor();
        DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
        //设定一个过滤器，一行一行的读取数据（/r/n）
       // chain.addLast("mychail",new ProtocolCodecFilter(new TextLineCodecFactory()));
        //设定过滤器已对象为单位来读取数据
        chain.addLast("objectFilter",new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        //设置服务端的消息处理器
        acceptor.setHandler(new MinaServerHandler());
        int port = 8888;
        try {
            //绑定端口，启动服务器(不会阻塞，理解返回）
            acceptor.bind(new InetSocketAddress(port));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Mina 服务已启动... 监听端口：" + port);

    }
}
