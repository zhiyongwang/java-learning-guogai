package com.guogai._02_tcp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

//从客户端发送文件到服务端，服务端保存到本地。并返回“发送成功”给客户端，并关闭相应的连接
public class TestTCP3 {
	//客户端
	@Test
	public void client() {
		Socket socket = null;
		OutputStream outputStream = null;
		FileInputStream fis = null;
		InputStream inputStream = null;
		try {
			//1创建Socket对象
			socket = new Socket(InetAddress.getByName("127.0.0.1"), 10086);
			//2从本地获取一个文件发送给服务端
			outputStream = socket.getOutputStream();
			fis = new FileInputStream(new File("Benz.jpg"));
			byte[] b = new byte[1024];
			int len;
			while((len = fis.read(b)) != -1) {
				outputStream.write(b, 0, len);
			}
			//表示已经发送完毕
			socket.shutdownOutput();
			//接收来自于服务端的应答
			inputStream = socket.getInputStream();
			byte[] b1 = new byte[1024];
			int len1;
			while((len1 = inputStream.read(b1)) != -1) {
				String str = new String(b1, 0, len1);
				System.out.println(str);
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if (outputStream !=null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if (socket !=null) {
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
		//1
		ServerSocket serverSocket = null;
		Socket socket = null;
		InputStream in = null;
		FileOutputStream fis = null;
		OutputStream out = null;
		try {
			//创建ServerSocket对象
			serverSocket = new ServerSocket(10086);
			//2调用accept()方法，返回一个Socket对象
			socket = serverSocket.accept();
			//3将客户端发送的信息保存到本地
			in = socket.getInputStream();
			fis = new FileOutputStream(new File("2.jpg"));
			byte[] b = new byte[1024];
			int len;
			while((len = in.read(b)) != -1){
				fis.write(b, 0, len);
			}
			//4发送接收成功信息。。
			out = socket.getOutputStream();
			out.write("你发送的图片我已成功接收".getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭下你相应的流
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
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
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		
		
		
		
	}

}
