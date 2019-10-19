package com.xuelin.coke.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

public class AbstractAppContext {

    protected ApplicationContext applicationContext;
    @Autowired
    protected ObjectMapper objectMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate2;
}
