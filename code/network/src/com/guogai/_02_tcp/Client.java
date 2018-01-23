package com.guogai._02_tcp;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws Exception, IOException {
		//Creates a stream socket and connects it to the specified port number at the specified IP address.
		Socket client = new  Socket("127.0.0.1", 6666);
		
		Scanner sc = new Scanner(client.getInputStream());
		while(sc.hasNextLine()){
			System.out.println(sc.nextLine());
		}
		sc.close();
		client.close();
		
	}
}
