package com.xuelin.coke.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 时间工具类
 */
public class TimeUtil {

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_PATTERN_MIN = "yyyyMMddHHmm";
    public static final String DATE_TIME_PATTERN_SHORT_YEAR = "yyMMddHHmmss";
    public static final String DATE_MILLISTIME_PATTERN = "yyyy-MM-dd HH:mm:ss.S"; // 毫秒

    private static final ThreadLocal<Map<String, SimpleDateFormat>> dateFormatThreadLocal = ThreadLocal.withInitial(HashMap::new);

    /**
     * 获得当前Unix Timestamp
     */
    public static int getUnixTimeStamp() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * Date类型转换为Unix Timestamp <br>
     * 如果Date为空，返回0
     */
    public static int dateToUnixTimestamp(Date date) {
        return date == null ? 0 : (int) (date.getTime() / 1000);
    }

    /**
     * Unix Timestamp转换为Date类型 <br>
     * 如果时间戳为0返回Null
     */
    public static Date unixTimestampToDate(long unixTimestamp) {
        return unixTimestamp == 0 ? null : new Date(unixTimestamp * 1000);
    }

    public static SimpleDateFormat parseDateFormat(String pattern) {
        Map<String, SimpleDateFormat> map = dateFormatThreadLocal.get();
        SimpleDateFormat sdf = map.get(pattern);
        if (sdf == null) {
            sdf = new SimpleDateFormat(pattern);
            map.put(pattern, sdf);
        }
        return sdf;
    }

    /**
     * 比较两个时间先后
     *
     * @param time1
     * @param time2
     * @return true a < b  false a > b
     */
    public static boolean compareDate(String time1, String time2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_PATTERN);
        //将字符串形式的时间转化为Date类型的时间
        Date a = sdf.parse(time1);
        Date b = sdf.parse(time2);
        //Date类的一个方法，如果a早于b返回true，否则返回false
        if (a.before(b))
            return true;
        else
            return false;
        /**
         * 根据将Date转换成毫秒
         if(a.getTime()-b.getTime()<0)
         return true;
         else
         return false;
         */
    }

    /**
     * 计算两个时间相差天数
     *
     * @return
     */
    public static int daysBetween(String smdate, String bdate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(smdate));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);
            return Integer.parseInt(String.valueOf(between_days));
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 当前时间 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String nowFormatDatatime() {
        String now = parseDateFormat(DATE_TIME_PATTERN).format(new Date());
        return now;
    }

    public static String getCurrTimeStr() {
        return TimeUtil.parseDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }

    public static String getTimeStr(long time) {
        return TimeUtil.parseDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(time));
    }

    /**
     * 根据Calendar. 获取当前时间
     *
     */
    public static int getCurCalendar(int calendar) {
        Calendar calendarDate = Calendar.getInstance();//可以对每个时间域单独修改
        return  calendarDate.get(calendar);
    }

    /**
     * 昨天，前几天
     *
     * @return
     */
    public static String yesterdayData(int amount) {
        if (Integer.valueOf(amount) == null) {
            amount = -1;
        }
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, amount);
        Date d = cal.getTime();

        SimpleDateFormat sp = new SimpleDateFormat(DATE_PATTERN);
        String y = sp.format(d);
        return y;
    }
}



