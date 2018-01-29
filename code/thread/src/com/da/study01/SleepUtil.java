package com.da.study01;

import java.util.concurrent.TimeUnit;

/**
 * Created by guo on 2018/1/29.
 */
public class SleepUtil {
    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
