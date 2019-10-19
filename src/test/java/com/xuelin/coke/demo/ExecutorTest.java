package com.xuelin.coke.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExecutorTest {

    @Test
    public void test01() throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        ScheduledFuture scheduledFuture = scheduledExecutorService.schedule(new Callable() {
                                  public Object call() throws Exception {
                                      System.out.println("Executed!");
                                      return "Called!";
                                  }
                              },
                        5,
                        TimeUnit.SECONDS);

        System.out.println("result = " + scheduledFuture.get());

        scheduledExecutorService.shutdown();
    }
}
