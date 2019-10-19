package com.xuelin.coke;

import com.xuelin.coke.config.EventConfig;
import com.xuelin.coke.event.DemoPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventTest {

    /**
     * 事件
     * https://www.jianshu.com/p/fd20dc8ea0dd
     * https://blog.wangqi.love/articles/Java/Spring%20Event%E4%BA%8B%E4%BB%B6%E9%80%9A%E7%9F%A5%E6%9C%BA%E5%88%B6.html
     */
    @Test
    public void demo1(){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
        DemoPublisher demoPublisher = context.getBean(DemoPublisher.class);
        demoPublisher.publish("Hello Application Context!");
        context.close();

        /*结果
        接收到了消息：Hello Application Context!
         */
    }
}
