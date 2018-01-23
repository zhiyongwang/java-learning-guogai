package com.guogai._01_base;
import java.net.InetAddress;
public class InetAddressDemo {
	public static void main(String args[]) throws Exception{
		//Determines the IP address of a host, given the host's name.
		//根据电脑主机名称获取ip地址
		//System.out.println(InetAddress.getByName("HOMEMIC-KHJ1MGG"));
		InetAddress ip = InetAddress.getByName("HOMEMIC-KHJ1MGG");
		System.out.println(ip.getHostName());        //Gets the host name for this IP address.
		System.out.println(ip.getHostAddress());  //Returns the IP address string in textual presentation.
		//Returns the address of the local host.
		InetAddress localHost = InetAddress.getLocalHost();
		System.out.println(localHost);
		System.out.println(localHost.getHostAddress());
		System.out.println(localHost.getHostName());
	}
}