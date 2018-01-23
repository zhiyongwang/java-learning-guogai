package com.guogai._02_tcp;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		//Creates a server socket, bound to the specified port.
		ServerSocket server = new ServerSocket(6666);
		//Listens for a connection to be made to this socket and accepts it.
		Socket client = server.accept();
		PrintStream ps = new PrintStream(client.getOutputStream());
		ps.println("郭小旭,你要好好加油！！！");
		//关闭资源
		server.close();
		client.close();
	}
}
