package com.gai.demo05;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.Random;

/**
 * Created by guo on 2018/1/28.
 */
public class Upload implements Runnable {
    private Socket socket;
    public Upload(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try{
            //通过客户端连接对象，获取字节输入流，读取客户端图片
            InputStream in = socket.getInputStream();
            //将目的的文件夹封装到File对象中
            File upload = new File("d:\\upload");
            if (!upload.exists()) {
                upload.mkdir();
            }
            //防止文件同名覆盖，从新定义名字
            //规则：域名+毫秒值+6位随机数
            String fileName = "guo" + System.currentTimeMillis() + new Random().nextInt(999999) + ".jpg";
            //创建字节输出流，将文件写入到目录的
            FileOutputStream fos = new FileOutputStream(upload + File.separator + fileName);
            byte[] b = new byte[1024];
            int len  = 0;
            while ((len = in.read(b)) != -1){
                fos.write(b,0,len);
            }

            //通过客户端连接对象，获取字节输出流
            socket.getOutputStream().write("上传成功".getBytes());

            //关闭相应的流
            fos.close();
            socket.close();


        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
