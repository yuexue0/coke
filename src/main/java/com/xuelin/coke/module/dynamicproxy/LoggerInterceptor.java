package com.xuelin.coke.module.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoggerInterceptor implements InvocationHandler {

    private Object target;//目标对象的引用，这里设计成Object类型，更具通用性

    public LoggerInterceptor(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("Entered "+target.getClass().getName()+"-"+method.getName()+",with arguments{"+args[0]+"}");
        Object result = method.invoke(target, args);//调用目标对象的方法
        System.out.println("Before return:"+result);
        return result;
    }
}
