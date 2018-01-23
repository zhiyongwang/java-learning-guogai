package com.guogai._02_tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;
/*
 * 客户端像服务器发送消息，服务端打印在控制台
 * 网络编程其实就是Socket编程
 */
public class TestTCP1 {
	
	//客户端
	@Test
	public void client() {
		Socket socket = null;
		OutputStream os = null;
		try {
			//创建一个Socket对象通过 构造器指明服务器的ip地址和端口号
			socket = new Socket(InetAddress.getByName("127.0.0.1"), 10086);
			//调用getOutputStream()方法像服务器发送数据
			os = socket.getOutputStream();
			//具体的发送过程
			os.write("郭晓旭你要好好的加油".getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭相应的流和Socket
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}
	//服务端
	@Test
	public void server() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		InputStream in = null;
		try {
			//创建一个ServerSocket对象，指明自身的端口
			serverSocket = new ServerSocket(10086);
			//调用accept()方法，返回一个Socket对象
			socket = serverSocket.accept();
			//调用Socket的getInputStream()方法获取从客户端输入的一个流
			in = socket.getInputStream();
			//对回去的输入流进行操作/
			byte [] b = new byte[1024];
			int len;
			while((len = in.read(b)) != -1) {
				String string = new String(b, 0, len);
				System.out.println(string);
			}
			System.out.println("收到来自" + socket.getInetAddress().getHostAddress());
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			//关闭相应的流和ServerSocket
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			if (serverSocket != null) {
				
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
