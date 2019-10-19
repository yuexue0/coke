package com.xuelin.coke.common.constants;

/**
 * 客户端scope枚举类
 */
public enum DemoEnum {

    COMPLETED("1", "任务完成"),
    NO_COMPLETED("0", "未完成");
    public String flag;
    public String desc;

    DemoEnum(String flag, String desc) {
        this.flag = flag;
        this.desc = desc;
    }


}
