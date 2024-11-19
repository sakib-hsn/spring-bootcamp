package com.codemaster.io;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogAdder implements InvocationHandler {
    private Object object;

    public LogAdder(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Calling time: " + System.currentTimeMillis());
        Object obj = method.invoke(object, args);
        System.out.println("Ending time: " + System.currentTimeMillis());
        return obj;
    }
}
