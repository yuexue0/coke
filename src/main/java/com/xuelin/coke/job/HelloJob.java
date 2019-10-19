package com.xuelin.coke.job;


import com.xuelin.coke.common.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HelloJob {
    private static final Logger logger = LoggerFactory.getLogger(Constants.ACCESS_LOGGER_NAME);

    @Scheduled(fixedRate = 5000)
    public void demoFixRate() {
        String s = "我是定时任务，执行时间："+System.currentTimeMillis();
        System.out.println(s);
        logger.info(s);
    }
}
