package com.guogai._01_base;

import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
//URL(Uniform Resource Locator)：统一资源定位符，它表示 Internet 上某一资源的地址。
public class URLDemo {
	public static void main(String[] args) throws Exception {
		/*
		 * protocol - the name of the protocol to use.
		 * host - the name of the host.
		 * port - the port number on the host.
		 * file - the file on the host
		 */
		URL url = new URL("HTTP", "127.0.0.1", 8080, "/index.jsp");
		/*
		 *  public String getProtocol(  )     获取该URL的协议名
			public String getHost(  )           获取该URL的主机名
			public String getPort(  )            获取该URL的端口号
			public String getPath(  )           获取该URL的文件路径
			public String getFile(  )             获取该URL的文件名
			public String getRef(  )             获取该URL在文件中的相对位置
			public String getQuery(   )        获取该URL的查询名

		 */
		URLConnection connection = url.openConnection();
		//获取资源的MIME
		System.out.println(connection.getContentType());
		//获取网页中的全部数据
		Scanner sc = new Scanner(connection.getInputStream());
		while(sc.hasNextLine()) {
			System.out.println(sc.nextLine());
		}
	}
}
