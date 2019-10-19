package com.xuelin.coke.demo;

import com.xuelin.coke.module.dynamicproxy.AppService;
import com.xuelin.coke.module.dynamicproxy.AppServiceImpl;
import com.xuelin.coke.module.dynamicproxy.LoggerInterceptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Proxy;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicproxyTest {

    @Test
    public void test(){
        AppService target = new AppServiceImpl();//生成目标对象
        //接下来创建代理对象
        AppService proxy = (AppService) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new LoggerInterceptor(target));
        proxy.createApp("James Test");
    }
}
