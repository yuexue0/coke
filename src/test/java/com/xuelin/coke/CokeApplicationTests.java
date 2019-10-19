package com.xuelin.coke;

import com.xuelin.coke.common.constants.ApolloConstans;
import com.xuelin.coke.common.constants.Constants;
import com.xuelin.coke.common.utils.TimeUtil;
import com.xuelin.coke.domain.WakeWordDO;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static com.xuelin.coke.common.utils.TimeUtil.parseDateFormat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CokeApplicationTests {

    @Autowired
    private HashMap<String, WakeWordDO> defaultWakeWordMap;

    @Test
    public void contextLoads() {

    }

    @Test
    public void time() {
        String now = parseDateFormat(TimeUtil.DATE_TIME_PATTERN).format(new Date());
        System.out.println(now);
    }

    @Test
    public void time2() {
        String startTime, endTime;
        Date date = new Date();
        int hour = TimeUtil.getCurCalendar(Calendar.HOUR_OF_DAY);
        if (hour == 0) {
            startTime = TimeUtil.yesterdayData(-1).concat(" ").concat("23").concat(":00:00");
            endTime = DateFormatUtils.format(date, "yyyy-MM-dd").concat(" ").concat(String.valueOf(hour)).concat(":00:00");
        } else {
            String strDate = DateFormatUtils.format(date, "yyyy-MM-dd");
            startTime = strDate.concat(" ").concat(String.valueOf(hour - 1)).concat(":00:00");
            endTime = strDate.concat(" ").concat(String.valueOf(hour)).concat(":00:00");
        }
        System.out.println("==========");
        System.out.println(hour);
        System.out.println(startTime);
        System.out.println(endTime);
    }

    @Test
    public void apolloValue() {

        String v = ApolloConstans.messageCompletedFalsePopupTrue;
        System.out.println(v);
    }

    @Test
    public void autowireBean(){
        WakeWordDO wakeWordDO = defaultWakeWordMap.get("orion.ovs.client.1508751991541");
        System.out.println(wakeWordDO);
    }

    @Test
    public void timeCost() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Thread.sleep(5000);
        System.out.println("cost:"+ stopWatch.getTime());
    }
}
