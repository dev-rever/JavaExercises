package com.reverchen.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Here's how to create "Thread" in three ways.
 */
public class ThreadMain {

    public static void main(String[] args) {

        // method 1: extend Thread class and override "run()" method.
        mThread mthread = new mThread();
        mthread.start();

        // method 2: implement Runnable interface and override "run()" method
        //           while posting to thread start.
        Thread thread1 = new Thread(new mRunnable());
        thread1.start();

        // method 3: implement Callable interface and override "call()" method,
        //           transfer callableImpl to FutureTask then post the futureTask
        //           to the thread startup, you can get result via futureTask.get()
        FutureTask<Object> ft = new FutureTask<>(new mCallable());
        Thread thread2 = new Thread(ft);
        thread2.start();
        try {
            Object result = ft.get();
            System.out.println("get future task result: " + result);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}