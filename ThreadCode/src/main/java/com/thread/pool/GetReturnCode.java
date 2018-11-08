package com.thread.pool;

import java.util.ArrayList;
import java.util.concurrent.*;

public class GetReturnCode {


    public static void main(String[] args) {
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        //创建两个有返回值的任务
        Callable c1 = new GetReturnCodeThread("A");
        Callable c2 = new GetReturnCodeThread("B");
        //执行任务并获取Future对象
        Future f1 = pool.submit(c1);
        Future f2 = pool.submit(c2);
        //从Future对象上获取任务的返回值，并输出到控制台
        try {
            System.out.println(">>>"+f1.get().toString());
            System.out.println(">>>"+f2.get().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //关闭线程池
        pool.shutdown();
    }
}

class GetReturnCodeThread implements Callable<String> {
    String code;

    public GetReturnCodeThread(String code){
        this.code=code;
    }
    @Override
    public String call() throws Exception {
        System.out.println(code+"执行");
        return code+":线程返回";
    }



}
