package com.guogai.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * Created by guo on 2018/1/24.
 * 服务端的消息处理器
 */
public class MinaServerHandler extends IoHandlerAdapter {
    //开始一次会话
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        System.out.println("client " + session.getRemoteAddress());
    }
    //一次会话结束
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);

        System.out.println("会话结束");
    }
    //接受消息
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
        //接收到的消息对象
       // String msg = (String) message;
        Message msg = (Message) message;
        System.out.println("收到客户端发来的消息：" + msg);
        msg.setInfo("越努力，越幸运");
        //像客户端发送消息
        session.write(msg);
    }
}
