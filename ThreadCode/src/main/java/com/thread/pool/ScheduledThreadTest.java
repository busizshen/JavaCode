package com.thread.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadTest {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        for(int i =0;i<10;i++){
            scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getId()+"  delay 1 seconds, and excute every 3 seconds");
                }
            }, 1, 3, TimeUnit.SECONDS);
        }


    }
}
