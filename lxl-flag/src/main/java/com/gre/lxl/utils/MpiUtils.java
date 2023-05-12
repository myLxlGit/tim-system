package com.gre.lxl.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 市场价格指数工具类
 *
 * @author lxl
 * @date 2021/12/1 10:53
 */
public class MpiUtils {

    /**
     * 日期转化字符串年月日
     *
     * @param date 日期
     * @return 字符串
     */
    public static String dateToString(Date date) {
        if (date == null) {
            return null;
        }
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * 日期转化字符串年月日时分秒
     *
     * @param date 日期
     * @return 字符串
     */
    public static String dateToStringH(Date date) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 获取两个时间之间的天数
     *
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 天数
     */
    public static int getDayDifference(Date startDate, Date endDate) {
        long startDateTime = startDate.getTime();
        long endDateTime= endDate.getTime();
        return (int) ((endDateTime - startDateTime) / (1000 * 3600 * 24));
    }

    /**
     * 获取初始化日期加或者减几天后新日期
     *
     * @param initDate 初始化日期
     * @param i 次数
     * @return 新日期
     */
    public static Date getQuizDate(Date initDate, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(initDate);
        calendar.add(Calendar.DAY_OF_MONTH,i);
        return calendar.getTime();
    }

}
