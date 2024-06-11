package com.reverchen.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Here's how to create "Thread" in three ways.
 */
public class ThreadMain {

    public static void main(String[] args) {

        createThreadExample();
        joinExample();

    }

    private static void createThreadExample() {
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

    private static void joinExample() {

        Runnable rb = () -> {
            for (int i = 1; i <= 50; i++) {
                System.out.println(Thread.currentThread().getName() + " \"i\" increased to: " + i);
            }
        };

        Thread t1 = new Thread(rb, "thread-1");
        Thread t2 = new Thread(rb, "thread-2");
        Thread t3 = new Thread(rb, "thread-3");

        t1.start();
        try {
            // The main thread will wait for the completion of the t1 thread before executing subsequent tasks.
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();
        t3.start();
    }
}