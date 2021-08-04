package com.vblessings.nhs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyright (c) 2020 Choice, Inc. All Rights Reserved. Choice Proprietary and
 * Confidential.
 * <p>
 *
 * @author wuya
 * @date 2020/7/17 10:42
 */
public class DateUtils {

    private static final String FORMAT_PATTERN_UNBLANK = "yyyyMMddHHmmss";

    public static final String YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HHMM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    private static SimpleDateFormat FORMAT_1;
    static {
        FORMAT_1 = new SimpleDateFormat("yyyy-MM-dd");
    }

    public static Date string2Date(String date){
        Date parse= null;
        try {
             parse = FORMAT_1.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    public static Date dateFormat(Date date){
        return string2Date(FORMAT_1.format(date));
    }
    /**
     * 获取当前时间数分钟后的时间
     *
     * @param duration
     * @return
     */
    public static Date getMinuteAfterCurrentTime(Date date, int duration) {
        Calendar curr = Calendar.getInstance();
        curr.setTime(date);
        curr.add(Calendar.MINUTE, duration);
        return curr.getTime();
    }

    /**
     * 获取当前时间数小时后的时间
     *
     * @param duration
     * @return
     */
    public static Date getHoursAfterCurrentTime(Date date, int duration) {
        Calendar curr = Calendar.getInstance();
        curr.setTime(date);
        curr.set(Calendar.HOUR_OF_DAY, curr.get(Calendar.HOUR_OF_DAY) + duration);
        return curr.getTime();
    }

    /**
     * 获取当前时间数天后的时间
     *
     * @param duration
     * @return
     */
    public static Date getDaysAfterCurrentTime(Date date, int duration) {
        Calendar curr = Calendar.getInstance();
        curr.setTime(date);
        curr.set(Calendar.DAY_OF_MONTH, curr.get(Calendar.DAY_OF_MONTH) + duration);
        return curr.getTime();
    }

    /**
     * 获取当前时间数天前的时间
     *
     * @param duration
     * @return
     */
    public static Date getDaysBeforeCurrentTime(Date date, int duration) {
        Calendar curr = Calendar.getInstance();
        curr.setTime(date);
        curr.set(Calendar.DAY_OF_MONTH, curr.get(Calendar.DAY_OF_MONTH) - duration);
        return curr.getTime();
    }

    /**
     * 获取当前时间数周后的时间
     *
     * @param duration
     * @return
     */
    public static Date getWeeksAfterCurrentTime(Date date, int duration) {
        Calendar curr = Calendar.getInstance();
        curr.setTime(date);
        curr.set(Calendar.WEEK_OF_MONTH, curr.get(Calendar.WEEK_OF_MONTH) + duration);
        return curr.getTime();
    }

    /**
     * 转换时间戳
     *
     * @return
     */
    public static long getTimeStamp(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_PATTERN_UNBLANK);
        return Long.parseLong(sdf.format(date));
    }

    /**
     * @description: 当前时间按小时单位增加
     * @author: huluobo
     * @param: [hours, date]
     * @return: java.util.Date
     * @date: 2019/5/17 11:23
     */
    public static Date addHourToDate(int hours, Date date) {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.HOUR_OF_DAY, hours);
        Date time = ca.getTime();
        return time;
    }

    /**
     * 获取当前时间
     *
     * @param
     * @return java.time.LocalDate
     * @author tianjiu
     * @date 2019/4/27
     */
    public static LocalDate getLocalDateOfNow() {
        return LocalDate.now();
    }

    /**
     * 转换时间类型
     *
     * @param dateStr
     * @return java.time.LocalDate
     * @author tianjiu
     * @date 2019/4/27
     */
    public static LocalDate getLocalDateByDateStr(String dateStr) {
        String[] dateArr = dateStr.split("-");
        return LocalDate.of(
                Integer.parseInt(dateArr[0]),
                Integer.parseInt(dateArr[1]),
                Integer.parseInt(dateArr[2])
        );
    }

    /**
     * 获取时间间隔（周）
     *
     * @param startDate
     * @param entDate
     * @return long
     * @author tianjiu
     * @date 2019/4/27
     */
    public static long getUntilNumberOfWeeks(LocalDate startDate, LocalDate entDate) {
        return startDate.until(entDate, ChronoUnit.WEEKS);
    }

    /**
     * 获取时间间隔（天）
     *
     * @param startDate
     * @param entDate
     * @return long
     */
    public static Long getUntilNumberOfDays(LocalDate startDate, LocalDate entDate) {
        return startDate.until(entDate, ChronoUnit.DAYS);
    }

    /**
     * 比较时间先后
     *
     * @param startDate
     * @param endDate
     * @return boolean
     * @author tianjiu
     * @date 2019/4/27
     */
    public static boolean compareDateBefore(LocalDate startDate, LocalDate endDate) {
        return startDate.isBefore(endDate);
    }

    /**
     * @description: 计算日期的时间差
     * @author: huluobo
     * @param: [date1, date2]
     * @return: int
     * @date: 2019/6/17 10:55
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        int days = (int) ((date1.getTime() - date2.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    /**
     * @description:时间大小比较
     * @author: huluobo
     * @param: [date1, date2]
     * @return: java.lang.Boolean
     * @date: 2019-06-22 15:23
     */
    public static Boolean timeIsMore(Date date1, Date date2) {
        int days = (int) ((date1.getTime() - date2.getTime()));
        if (days >= 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * 获取日期格式化后字符串
     *
     * @param date
     * @param formatPattern
     * @return
     */
    public static String dateToStringByFormat(Date date, String formatPattern) {
        SimpleDateFormat format = new SimpleDateFormat(formatPattern);
        return format.format(date);
    }

    /**
     * 字符串转换为日期
     *
     * @param dateStr
     * @param formatPattern
     * @return
     * @throws ParseException
     */
    public static Date stringToDateByFormat(String dateStr, String formatPattern) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(formatPattern);
        return format.parse(dateStr);
    }

    /**
     * 将时间去掉时分秒
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date dateToNoTime(Date date) throws ParseException {
        if (date == null) {
            return null;
        }
        String s = FORMAT_1.format(date);
        return FORMAT_1.parse(s);
    }

    public static Date getStartTime0fDay(Date date) {
        String s = dateToStringByFormat(date, "yyyy-MM-dd");
        try {
            return stringToDateByFormat(s + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date getEndTimeOfDay(Date date) {
        String s = dateToStringByFormat(date, "yyyy-MM-dd");
        try {
            return stringToDateByFormat(s + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param format
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }

    public static int getAgeByBirth(Date birthDay) {
        if(birthDay == null){
            return 0;
        }
        int age = 0;
        Calendar cal = Calendar.getInstance();
        //出生日期晚于当前时间，无法计算
        if (cal.before(birthDay)) {
            return 0;
        }
        //当前年份
        int yearNow = cal.get(Calendar.YEAR);
        //当前月份
        int monthNow = cal.get(Calendar.MONTH);
        //当前日期
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        //计算整岁数
        age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }//当前日期在生日之前，年龄减一
            } else {
                age--;//当前月份在生日之前，年龄减一
            }
        }
        return age;
    }

    /**
     * 判断是否符合日期格式，是的话并返回
     *@author chencaihui
     *@datetime 创建时间：2017年3月27日 下午2:41:38
     * @param dateStr
     * @return
     */
    public static Date isDate(String dateStr) {
        Date formatDate = null;
        if(isDateOfYmd(dateStr)){//先校验yyyy-mm-dd结构是否正确
            try {
                formatDate = new SimpleDateFormat(YYYY_MM_DD_HHMMSS).parse(dateStr);
            } catch (ParseException e) {
                try {
                    formatDate = new SimpleDateFormat(YYYY_MM_DD_HHMM).parse(dateStr);
                } catch (ParseException e1) {
                    try {
                        formatDate = new SimpleDateFormat(YYYY_MM_DD).parse(dateStr);
                    } catch (ParseException e2) {}
                }
            }
        }
        return formatDate;
    }

    /**
     * 校验日期是否合法
     * yyyy-mm-dd
     */
    public static boolean isDateOfYmd(String value){
        if(ObjectUtil.isNotNull(value) && value.length()>=10){
            String dateStr = value.substring(0, 10);
            String rexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
            Pattern pat = Pattern.compile(rexp);
            Matcher mat = pat.matcher(dateStr);
            return mat.matches();
        }
        return false;
    }
}
