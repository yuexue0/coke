package com.xuelin.coke.config;


import com.xuelin.coke.domain.WakeWordDO;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
@EnableCaching
public class BeanConfig {

    @Bean
    public HashMap<String, WakeWordDO> defaultWakeWordMap() {
        HashMap<String, WakeWordDO> wakeWordDOMap = new HashMap<>();

        WakeWordDO wakeWordDO1 = new WakeWordDO();
        wakeWordDO1.setClientId("orion.ovs.client.1508751991541");
        wakeWordDO1.setWakeWord("小雅小雅");
        wakeWordDO1.setWakeWordSpelling("x:iao,ii:ia,x:iao,ii:ia");
        wakeWordDOMap.put(wakeWordDO1.getClientId(), wakeWordDO1);

        WakeWordDO wakeWordDO2 = new WakeWordDO();
        wakeWordDO2.setClientId("orion.ovs.client.1503470121484");
        wakeWordDO2.setWakeWord("小豹小豹");
        wakeWordDO2.setWakeWordSpelling("x:iao,b:ao,x:iao,b:ao");
        wakeWordDOMap.put(wakeWordDO2.getClientId(), wakeWordDO2);

        return wakeWordDOMap;
    }
}
