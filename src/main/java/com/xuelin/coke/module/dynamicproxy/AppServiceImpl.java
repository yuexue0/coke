package com.xuelin.coke.module.dynamicproxy;

public class AppServiceImpl implements AppService{

    @Override
    public boolean createApp(String name) {
        System.out.println("App["+name+"] has been created.");
        return true;
    }
}
