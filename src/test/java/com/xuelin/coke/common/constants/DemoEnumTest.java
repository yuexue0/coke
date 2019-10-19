package com.xuelin.coke.common.constants;

import org.junit.Test;

import javax.sql.rowset.serial.SerialStruct;

import static org.junit.Assert.*;

public class DemoEnumTest {

    @Test
    public void setFlag() {
        String flag = DemoEnum.COMPLETED.flag;
        String desc = DemoEnum.COMPLETED.desc;
        System.out.println(flag);
        System.out.println(desc);
    }
}