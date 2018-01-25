# 网络编程知识点
如今，TCP/IP已成为计算机网络协议事实上的 标准，而Java凭借其跨平台特性和对网络编程的强大的支持能力。在网络编程中占主导地位。<br>
1、计算机网络：把分布在不同地理区域的计算机专门的外部设备用用通信线路互联成一个规模大、功能强的网络系统，从而使众多的计算机可以方便的互相传递信息，共享硬件、软件、数据信息等资源。我们把这些 主机和路由器，路由器非常重要，因为想要直接将所有不同的主机连接起来是不可能的。一些主机先连接到路由器，这些路由器在连接到其他路由器。这样就形成了网络。<br>
2、网络通信协议：协议相当于相互通信的程序间达成的一种约定，它规定了分组报文的交互方式和他们包含的意义。一组协议规定了分组报文的结构以及怎样对报文中包含的信息进行解析。设计一组协议通常是为了在一定约束条件下解决某一特定问题。<br>
2、1HTTP( HyperText Transfer Protocol)超文本传输协议是为了解决在服务端间传递超文本对象的问题，这些超文本对象在服务端中创建、存储，并由Web浏览器进行可视化，以使其对用户有用。<br>
2.2TCP/IP协议有时会成为协议族。主要协议有：[TCP协议](https://zh.wikipedia.org/wiki/%E4%BC%A0%E8%BE%93%E6%8E%A7%E5%88%B6%E5%8D%8F%E8%AE%AE)，[IP协议](https://zh.wikipedia.org/wiki/%E7%BD%91%E9%99%85%E5%8D%8F%E8%AE%AE)，和[UDP协议](https://zh.wikipedia.org/wiki/%E7%94%A8%E6%88%B7%E6%95%B0%E6%8D%AE%E6%8A%A5%E5%8D%8F%E8%AE%AE)，各种协议分层组织，

- TCP(Transmission Control Protocol)是一种面向连接的、可靠的、基于字节流的传输层通信协议.

- IP(Internet Protocol)提过了一种数据报服务，每组分组报文都由网络独立处理和分发，就像信件或包裹通过快递发送一样。

- UDP(User Datagram Protocol)是一种无连接的协议，每个数据报都是一个独立的信息，包括完整的源地址或目的地址，它在网络上以任何可能的路径传往目的地，因此能否到达目的地，到达目的地的时间以及内容的正确性都是不能被保证的，每个被传输的数据报必须限定在64KB之内。<br>

- 分享一张图片。截图只是其中一部分。([原图点击这里下载](http://www.colasoft.com.cn/download/network-protocol-map-2017.zip))
![](https://i.imgur.com/jhkHEVC.png)

- 关于各种协议的文档点击这里([下载](https://github.com/guoxiaoxu/java-learning-guogai/tree/master/doc))

3、实现TCP程序，需要编写服务器端和客户端，Java API为我们提供了java.net包，为实现网络应用程序提供类。

- ServerSocket ：此类实现服务器套接字。
- Socket ：此类实现客户端套接字（也可以就叫“套接字”）。

套接字是软件端点，用于建立服务器与客户端之间的双向通信，套接字将服务端程序关联某个指定IP和端口。服务器会创建服务器套接字，并持续监听到达服务器的所有请求。客户端创建连接到服务端的套接字。服务器可以接受或拒绝客户端的请求。

Socket是网络驱动层提供给应用程序编程的接口和一种机制。

*[代码示例请点击这里查看](https://github.com/guoxiaoxu/java-learning-guogai/tree/master/code/network/src/com/guogai)*（喜欢的可以点个star）

感谢：扣丁学堂，尚硅谷

参考：《Java 7编程高级进阶》萨朗 (Poornachandra Sarang) (作者),‎ 曹如进 (译者),‎ 张方勇 (译者)、《Java TCP/IP Socket编程(原书第2版)》

补充：有什么不对的地方，请大神指出，谢谢。
