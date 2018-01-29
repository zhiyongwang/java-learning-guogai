package com.da.study;

/**
 * Created by guo on 2018/1/29.
 * 多线程一定快吗？
 * <p>
 * concurrentcy:2ms,b=-10001
 * serial:0ms,b=-10001,a=50005
 * <p>
 * concurrentcy:9ms,b=-100001
 * serial:4ms,b=-100001,a=500005
 * <p>
 * 只有超过百万才比并行快2ms
 * concurrentcy:6ms,b=-1000001
 * serial:8ms,b=-1000001,a=5000005
 * <p>
 * 千万次运算 q
 * concurrentcy:13ms,b=-10000001
 * serial:20ms,b=-10000001,a=50000005
 * <p>
 * 一亿
 * concurrentcy:67ms,b=-100000001
 * serial:112ms,b=-100000001,a=500000005
 * <p>
 * 当并发执行累加操作不超过百万次时，速度会比串行执行累加操作要慢，
 * 因为线程有创建和上下文切换的开销
 */
public class ConcurrencyTest {
    private static final long count = 100000001;

    public static void main(String[] args) throws InterruptedException {
        conCurrentcy();
        serial();

    }

    /**
     * 并发
     */
    private static void conCurrentcy() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                long a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        thread.join();
        System.out.println("concurrentcy:" + time + "ms,b=" + b);
    }

    /**
     * 串行
     */
    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial:" + time + "ms,b=" + b + ",a=" + a);
    }
}
