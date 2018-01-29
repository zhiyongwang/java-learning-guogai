package com.da.study01;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Created by guo on 2018/1/29.
 * 使用JMX来查看一个Java程序包含那些线程
 */
public class MultiThrad {
    public static void main(String[] args) {
        //获取Java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //不需要获取同步的monitor和synchroniized信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        //遍历线程信息，仅打印线程id和线程名字
        for(ThreadInfo threandInfo : threadInfos) {
            System.out.println("[" + threandInfo.getThreadId() + "]" + threandInfo.getThreadName());
        }
    }
}
