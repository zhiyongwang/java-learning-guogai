package com.guogai._03_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.junit.Test;

public class TestUDP {
	// 发送端
	@Test
	public void send() {
		DatagramSocket dSocket = null;
		try {
			dSocket = new DatagramSocket();
			byte[] b = "越努力，越幸运".getBytes();
			// 创建一个数据报，每个数据报不能大于64k。
			DatagramPacket dPacket = new DatagramPacket(b, 0, b.length, InetAddress.getByName("127.0.0.1"), 10086);
			dSocket.send(dPacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (dSocket != null) {
				dSocket.close();

			}
		}
	}

	// 接受端
	@Test
	public void receive() {
		DatagramSocket dSocket = null;
		try {
			dSocket = new DatagramSocket(10086);
			byte[] bs = new byte[1024];
			DatagramPacket dPacket = new DatagramPacket(bs, 0, bs.length);
			dSocket.receive(dPacket);
			String String = new String(dPacket.getData(), 0, dPacket.getLength());
			System.out.println(String);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (dSocket != null) {
				dSocket.close();

			}
		}

	}
}
