package com.reverchen.thread;

import java.util.concurrent.Callable;

public class mCallable implements Callable<Object> {

    @Override
    public Object call() throws Exception {
        System.out.println(
                "Preform some tasks on implemented Callable<T> class," +
                        "and this method you can return result to the caller"
        );
        return new Object();
    }
}
