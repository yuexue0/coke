package com.xuelin.coke;

import com.xuelin.coke.config.EventConfig;
import com.xuelin.coke.event.DemoPublisher;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
@MapperScan({"com.xuelin.coke.dao"})
public class CokeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CokeApplication.class, args);

        //或者try-catch
        /*
        try {
            //Application
            SpringApplication springApplication = new SpringApplication(Application.class);
            springApplication.run(args);

        } catch (Exception e) {
            e.printStackTrace();
        }
        */
    }

}
