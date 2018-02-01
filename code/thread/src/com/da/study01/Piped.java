package com.da.study01;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * Created by guo on 2018/1/31.
 * 作用：用来接收main线程的输入，任何main线程的输入均可以通过PipedWriter,而PrintThread在另一端通过PipedReader读出并打印
 */
public class Piped {
    public static void main(String[] args) throws IOException {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        //将输入和输出流进行连接 ，否则在使用中会抛出IOException
        out.connect(in);
        Thread PrintThread = new Thread( new Print(in),"PrintThread");
        PrintThread.start();
        int receive = 0;
        try {
            while ((receive = System.in.read()) != -1) {
                out.write(receive);
            }
        } finally {
            out.close();
        }
    }
    static class Print implements Runnable {
        private PipedReader in;
        public Print(PipedReader in) {
            this.in = in ;
        }
        @Override
        public void run() {
            int receive = 0;
                try {
                    while ((receive = in.read()) != -1) {
                        System.out.print((char) receive);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
