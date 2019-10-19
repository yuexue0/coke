package com.xuelin.coke.common.constants;


import com.ctrip.framework.apollo.spring.annotation.ApolloJsonValue;
import org.springframework.stereotype.Component;

@Component
public class ApolloConstans {

    @ApolloJsonValue("${aitoken.attendance.messageCompletedFalsePopupTrue}")
    public static String messageCompletedFalsePopupTrue;
}
