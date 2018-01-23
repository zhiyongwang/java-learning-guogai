package com.guogai._02_tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

//客户端给服务端发送消息，服务端将信息打印在控制台。同时发送“已收到”给客户端
public class TestTCP2 {
	// 客户端
	@Test
	public void client() {
		Socket socket = null;
		OutputStream os = null;
		InputStream in = null;
		try {
			socket = new Socket(InetAddress.getByName("127.0.0.1"), 10086);
			os = socket.getOutputStream();
			os.write("我应该怎么好学技术？".getBytes());
			//告诉服务器已经发送完毕
			socket.shutdownOutput();
			in = socket.getInputStream();
			byte[] b = new byte[1024];
			int len;
			while ((len = in.read(b)) != -1) {
				String string = new String(b, 0, len);
				System.out.println(string);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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

	// 服务端
	@Test
	public void server() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		InputStream in = null;
		OutputStream out = null;
		try {
			serverSocket = new ServerSocket(10086);
			socket = serverSocket.accept();
			in = socket.getInputStream();
			byte[] b = new byte[1024];
			int len;
			while ((len = in.read(b)) != -1) {
				String string = new String(b, 0, len);
				System.out.println(string);
			}
			out = socket.getOutputStream();
			out.write("使劲的敲代码，调试，理解背后的原因".getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
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
