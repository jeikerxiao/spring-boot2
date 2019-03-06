package com.jeiker.weixin.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Description: weixin-java-mp-demo-springboot
 * User: jeikerxiao
 * Date: 2019/3/4 5:28 PM
 */
public class DateUtils {


    /**
     * 日期格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 日期格式
     */
    public static final String DEFAULT_DATE_FORMAT_LI = "yyyy/MM/dd";
    /**
     * 日期时间格式
     */
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期时间格式
     */
    public static final String ACTIVITY_DATETIME_FORMAT = "MM/dd/yyyy HH:mm:ss";
    /**
     * 日期时间格式
     */
    public static final String PAYMENT_DATETIME_FORMAT = "yyyyMMddHHmmss";
    /**
     * 时间格式
     */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    /**
     * REDIS key相关的日期格式
     */
    public static final String REDIS_DATE_FORMAT = "yyyyMMdd";

    /**
     * 时间常量 - 秒
     */
    public static final long SECOND = 1000L;
    /**
     * 时间常量 - 分
     */
    public static final long MIN = 60000L;
    /**
     * 时间常量 - 小时
     */
    public static final long HOUR = 3600000L;
    /**
     * 时间常量 - 天
     */
    public static final long DAY = 86400000L;
    /**
     * 每天小时数
     */
    private static final long HOURS_PER_DAY = 24;
    /**
     * 每小时分钟数
     */
    private static final long MINUTES_PER_HOUR = 60;
    /**
     * 每分钟秒数
     */
    private static final long SECONDS_PER_MINUTE = 60;
    /**
     * 每秒的毫秒数
     */
    private static final long MILLIONSECONDS_PER_SECOND = 1000;
    /**
     * 每分钟毫秒数
     */
    private static final long MILLIONSECONDS_PER_MINUTE = MILLIONSECONDS_PER_SECOND * SECONDS_PER_MINUTE;
    /**
     * 每天毫秒数
     */
    private static final long MILLIONSECONDS_SECOND_PER_DAY = HOURS_PER_DAY * MINUTES_PER_HOUR * SECONDS_PER_MINUTE * MILLIONSECONDS_PER_SECOND;

    private DateUtils() {
    }

    /**
     * 将yyyy-MM-dd格式的字符串转换为日期对象
     *
     * @param date 待转换字符串
     * @return 转换后日期对象
     * @see #getDate(String, String, Date)
     */
    public static Date getDate(String date) {
        return getDate(date, DEFAULT_DATE_FORMAT, null);
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss格式的字符串转换为日期对象
     *
     * @param date 待转换字符串
     * @return 转换后日期对象
     * @see #getDate(String, String, Date)
     */
    public static Date getDateTime(String date) {
        return getDate(date, DEFAULT_DATETIME_FORMAT, null);
    }

    /**
     * 将指定格式的字符串转换为日期对象
     *
     * @param date   待转换字符串
     * @param format 日期格式
     * @return 转换后日期对象
     * @see #getDate(String, String, Date)
     */
    public static Date getDate(String date, String format) {
        return getDate(date, format, null);
    }

    /**
     * 将指定格式的字符串转换为日期对象
     *
     * @param date   日期对象
     * @param format 日期格式
     * @param defVal 转换失败时的默认返回值
     * @return 转换后的日期对象
     */
    public static Date getDate(String date, String format, Date defVal) {
        if (StringUtils.isEmpty(date) || StringUtils.isEmpty(format)) {
            return null;
        }
        Date d;
        try {
            d = DateFormatThreadLocal.getFormat(format).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            d = defVal;
        }
        return d;
    }

    /**
     * 将日期对象格式化成yyyy-MM-dd格式的字符串
     *
     * @param date 待格式化日期对象
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String formatDate(Date date) {
        return formatDate(date, DEFAULT_DATE_FORMAT, null);
    }

    /**
     * 将日期对象格式化成yyyy-MM-dd HH:mm:ss格式的字符串
     *
     * @param date 待格式化日期对象
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String forDatetime(Date date) {
        return formatDate(date, DEFAULT_DATETIME_FORMAT, null);
    }

    /**
     * 将日期对象格式化成HH:mm:ss格式的字符串
     *
     * @param date 待格式化日期对象
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String formatTime(Date date) {
        return formatDate(date, DEFAULT_TIME_FORMAT, null);
    }

    /**
     * 将日期对象格式化成指定类型的字符串
     *
     * @param date   待格式化日期对象
     * @param format 格式化格式
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String formatDate(Date date, String format) {
        return formatDate(date, format, null);
    }

    /**
     * 将日期对象格式化成指定类型的字符串
     *
     * @param date   待格式化日期对象
     * @param format 格式化格式
     * @param defVal 格式化失败时的默认返回空
     * @return 格式化后的字符串
     */
    public static String formatDate(Date date, String format, String defVal) {
        if (date == null || StringUtils.isEmpty(format)) return defVal;
        String ret;
        try {
            ret = DateFormatThreadLocal.getFormat(format).format(date);
        } catch (Exception e) {
            ret = defVal;
        }
        return ret;
    }

    /**
     * 返回指定日期加上days天后的日期
     *
     * @param date
     * @param days
     * @return
     */
    public static Date plusDays(Date date, int days) {
        return changeDays(date, days);
    }

    /**
     * 返回今日加上days天后的日期
     *
     * @param days
     * @return
     */
    public static Date plusTodayDays(int days) {
        return plusDays(now(), days);
    }

    /**
     * 返回今日减去days天后的日期
     *
     * @param days
     * @return
     */
    public static Date minusTodayDays(int days) {
        return minusDays(now(), days);
    }

    /**
     * 返回指定日期减去days天后的日期
     *
     * @param date
     * @param days
     * @return
     */
    public static Date minusDays(Date date, int days) {
        return changeDays(date, -days);
    }

    /**
     * 返回指定日期改变days后的日期
     *
     * @param date
     * @param days
     * @return
     */
    private static Date changeDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return cal.getTime();
    }

    /**
     * 返回指定日期改变hours后的日期
     *
     * @param date
     * @param hours(负数表示减去)
     * @return
     */
    public static Date changeHours(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hours);
        return cal.getTime();
    }

    /**
     * 返回指定日期改变minutes后的日期
     *
     * @param date
     * @param minutes(负数表示减去)
     * @return
     */
    public static Date changeMinutes(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    /**
     * 返回指定日期改变seconds后的日期
     *
     * @param date
     * @param seconds(负数表示减去)
     * @return
     */
    public static Date changeSeconds(Date date, int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.SECOND, seconds);
        return cal.getTime();
    }

    /**
     * 获取今日某个小时零时零分的日期
     *
     * @param hour 当天小时数
     * @return
     */
    public static Date getTodayByHour(int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取某日某个小时零时零分的日期
     *
     * @param now
     * @param hour
     * @return
     */
    public static Date getDateByHour(long now, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(now);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取某日某个小时零时零分的时间戳
     *
     * @param now
     * @param hour
     * @return
     */
    public static long getTimeByHour(long now, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(now);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获取今日某个小时零时零分的日期
     *
     * @param hour   当天小时数
     * @param minute 当天分钟数
     * @return
     */
    public static Date getTodayByTime(int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }


    /**
     * 现在时间
     *
     * @return
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return
     */
    public static long getTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 根据时间戳获取日期
     *
     * @param time
     * @return
     */
    public static Date date(long time) {
        return new Date(time);
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return
     */
    public static long getTimeSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 返回以毫秒为单位的系统时间
     *
     * @return
     */
    public static long getSystemTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 系统时间
     *
     * @return
     */
    public static Date getSystemDate() {
        return new Date();
    }

    /**
     * 获取当前日期的第二天
     *
     * @param date
     * @return
     */
    public static Date getTomorrow(Date date) {
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, 1);
            return cal.getTime();
        } else {
            return null;
        }
    }


    public static Date getDayLater(Date date, int laterDay) {
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, laterDay);
            return cal.getTime();
        } else {
            return null;
        }
    }

    /**
     * 获得当前时间sql.date
     */
    public static java.sql.Date getTodaySqlDate() {
        return new java.sql.Date(getTimeMillis());
    }

    /**
     * 获取今天日期, 格式: YYYY-MM-DD
     *
     * @return
     */
    public static String getTodayStr() {
        return formatDate(now(), DEFAULT_DATE_FORMAT);
    }

    /**
     * 获取今天日期, 格式: YYYY-MM-DD
     *
     * @return
     */
    public static String getTodayTimeStr() {
        return formatDate(now(), DEFAULT_DATETIME_FORMAT);
    }

    /**
     * 比较传入日期与当前日期相差的天数
     *
     * @param d
     * @return
     */
    public static int intervalDay(Date d) {
        return intervalDay(d, now());
    }

    /**
     * 比较两个日期相差的天数
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int intervalDay(Date d1, Date d2) {
        long intervalMillSecond = setToDayStartTime(d1).getTime() - setToDayStartTime(d2).getTime();
        //相差的天数 = 相差的毫秒数 / 每天的毫秒数 (小数位采用去尾制)
        return (int) (intervalMillSecond / MILLIONSECONDS_SECOND_PER_DAY);
    }

    /**
     * 查看今日是否在有效日期之内
     *
     * @param start 日期格式YYYY-MM-DD
     * @param end   日期格式YYYY-MM-DD
     * @return
     */
    public static boolean isBetweenDays(String start, String end) {
        Date today = now();
        Date startDate = getDate(start);
        Date endDate = getTomorrow(getDate(end));
        if (startDate == null && endDate == null) return false;
        if (startDate.before(endDate) && today.after(startDate) && today.before(endDate)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 查看日期是否在有效日期之内
     *
     * @param startDate
     * @param endDate
     * @param aberration 误差(秒)
     * @return
     */
    public static boolean isBetweenDays(Date date, Date startDate, Date endDate, int aberration) {
        if (date != null && startDate == null && endDate == null) return false;
        startDate = changeSeconds(startDate, -aberration);
        endDate = changeSeconds(endDate, aberration);
        if (startDate.before(endDate) && date.after(startDate) && date.before(endDate)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 查看今日是否在有效日期之内
     *
     * @param days   start(YYYY-MM-DD)|end(YYYY-MM-DD)
     * @param filter 分隔符（\\|）
     * @return
     */
    public static boolean isDateValid(String days, String filter) {
        if (!StringUtils.isEmpty(days)) {
            String[] dateArr = days.split(filter);
            if (dateArr != null && dateArr.length == 2 && isBetweenDays(dateArr[0], dateArr[1])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 今日日期的字符串是否正确
     *
     * @param days
     * @return
     */
    public static boolean isDateToady(String days, String filter) {
        if (!StringUtils.isEmpty(days)) {
            String[] dateArr = days.split(filter);
            if (dateArr != null && dateArr.length == 2) {
                String today = dateArr[0];
                String todayStr = getTodayStr();
                if (today.equals(todayStr)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 将时间调整到当天0:0:0
     *
     * @param date
     * @return
     */
    public static Date setToDayStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 将时间调整到当天23:59:59
     *
     * @param date
     * @return
     */
    public static Date setToDayEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);
        return calendar.getTime();
    }


    /**
     * 从现在到凌晨还有多少秒
     *
     * @return
     */
    public static long caluteDayTime() {
        try {
            Date now = now();
            SimpleDateFormat dateFormat = DateFormatThreadLocal.getFormat("yyyy-MM-dd");
            String str = dateFormat.format(now);
            long time = dateFormat.parse(str).getTime();
            dateFormat = DateFormatThreadLocal.getFormat("yyyy-MM-dd HH:mm:ss");
            String str2 = dateFormat.format(now);
            long time2 = dateFormat.parse(str2).getTime();
            long minusTime = time2 - time;
            return (long) ((DAY - minusTime) / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
            return (long) DAY / 1000;
        }
    }

    /**
     * 判断当前时间
     *
     * @return
     */
    public static String getDateStatus() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour >= 6 && hour < 12) {
            return "morning";
        } else if (hour >= 12 && hour < 18) {
            return "noon";
        } else if (hour >= 18 && hour < 24) {
            return "evning";
        } else {
            return "midnight";
        }
    }

    /**
     * 获得两个日期之间相差的分钟数。（date1 - date2）
     *
     * @param date1
     * @param date2
     * @return 返回两个日期之间相差的分钟数值
     */
    public static int intervalMinutes(Date date1, Date date2) {
        long intervalMillSecond = date1.getTime() - date2.getTime();

        //相差的分钟数 = 相差的毫秒数 / 每分钟的毫秒数 (小数位采用进位制处理，即大于0则加1)
        return (int) (intervalMillSecond / MILLIONSECONDS_PER_MINUTE
                + (intervalMillSecond % MILLIONSECONDS_PER_MINUTE > 0 ? 1 : 0));
    }

    /**
     * 获得两个日期之间相差的秒数差（date1 - date2）
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int intervalSeconds(Date date1, Date date2) {
        long intervalMillSecond = date1.getTime() - date2.getTime();
        return (int) (intervalMillSecond / MILLIONSECONDS_PER_SECOND
                + (intervalMillSecond % MILLIONSECONDS_PER_SECOND > 0 ? 1 : 0));
    }

    /**
     * 获得两个日期之间相差的毫秒数差（date1 - date2）
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long intervalMiliSeconds(Date date1, Date date2) {
        long intervalMillSecond = date1.getTime() - date2.getTime();
        return intervalMillSecond;
    }

    /**
     * 根据具体的小时分秒获取今天的具体时间
     *
     * @param hourOfDay
     * @param minute
     * @param second
     * @return
     */
    public static Date getTodayTime(int hourOfDay, int minute, int second) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(now());
        ca.set(Calendar.HOUR_OF_DAY, hourOfDay);
        ca.set(Calendar.MINUTE, minute);
        ca.set(Calendar.SECOND, second);
        return ca.getTime();
    }

    /**
     * 根据具体的小时分秒获取某日的具体时间
     *
     * @param date
     * @param hourOfDay
     * @param minute
     * @param second
     * @return
     */
    public static Date getTime(Date date, int hourOfDay, int minute, int second) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.HOUR_OF_DAY, hourOfDay);
        ca.set(Calendar.MINUTE, minute);
        ca.set(Calendar.SECOND, second);
        return ca.getTime();
    }

    /**
     * 获取到明天0点还有几秒
     *
     * @return
     */
    public static int getTodaySeconds() {
        Date now = now();
        Calendar ca = Calendar.getInstance();
        ca.setTime(now);
        ca.set(Calendar.HOUR_OF_DAY, 24);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        Date tomorrow = ca.getTime();
        long intervalMillSecond = tomorrow.getTime() - now.getTime();
        return (int) (intervalMillSecond / MILLIONSECONDS_PER_SECOND
                + (intervalMillSecond % MILLIONSECONDS_PER_SECOND > 0 ? 1 : 0));
    }

    /**
     * 根据生日获取年龄
     *
     * @param birthday
     * @return
     */
    public static int getAge(Date birthday) {
        Calendar now = Calendar.getInstance();
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthday);
        //取得生日年份
        int year = birth.get(Calendar.YEAR);
        //年龄
        int age = now.get(Calendar.YEAR) - year;
        //修正
        now.set(Calendar.YEAR, year);
        age = (now.before(birth)) ? age - 1 : age;
        return age;
    }

    public static boolean isSameYear(Date d1, Date d2) {
        if (d1 == null || d2 == null)
            return false;
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(d1.getTime());
        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(d2.getTime());
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR);
    }

    /**
     * d1 和 d2 是同一小时
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean isSameHour(Date d1, Date d2) {
        if (d1 == null || d2 == null)
            return false;
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(d1.getTime());
        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(d2.getTime());

        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
                && c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)
                && c1.get(Calendar.HOUR_OF_DAY) == c2.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * d1 和 d2 是同一天
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean isSameDate(Date d1, Date d2) {
        if (d1 == null || d2 == null)
            return false;
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(d1.getTime());
        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(d2.getTime());

        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
                && c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 两个时间戳是否同一天
     *
     * @param timestamp1
     * @param timestamp2
     * @return
     */
    public static boolean isSameDate(long timestamp1, long timestamp2) {
        return isSameDate(new Date(timestamp1), new Date(timestamp2));
    }

    /**
     * d1 和 d2 是同一个月
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean isSameMonth(Date d1, Date d2) {
        if (d1 == null || d2 == null)
            return false;
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(d1.getTime());
        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(d2.getTime());

        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH);
    }

    /**
     * 判断是否d2是d1的后一天
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean isContinueDay(Date d1, Date d2) {
        if (d1 == null || d2 == null)
            return false;
        if (intervalDay(d1, d2) == 1)
            return true;
        return false;
    }

    /**
     * 是否是一周的结束,适用于每周失效的操作
     *
     * @return 是否忽略判断更新缓存中的值
     */
    public static boolean isEndOfTheWeek() {
        Calendar c1 = GregorianCalendar.getInstance();
        return c1.get(Calendar.DAY_OF_WEEK) == 1;
    }

    /**
     * 改变日期字符串的格式
     *
     * @param dateTime
     * @param oldFormat
     * @param newFormat
     * @return
     */
    public static String changeDateFormat(String dateTime, String oldFormat, String newFormat) {
        return formatDate(getDate(dateTime, oldFormat), newFormat);
    }

    /**
     * 从本地1970 年 1 月 1 日 00:00:00到time之间经过的天数
     *
     * @param time
     * @return
     */
    public static long getDay(long time) {
        int offset = TimeZone.getDefault().getOffset(time);
        return (time + offset) / MILLIONSECONDS_SECOND_PER_DAY;
    }

    /**
     * 是否是新的一天
     *
     * @param before
     * @param now
     * @return
     */
    public static boolean isNewDay(long before, long now) {
        return getDay(now) > getDay(before);
    }

    /**
     * 把当前时间转换成日期，如12:00:00转换成今日日期
     *
     * @param date
     * @param timeStr
     * @return
     */
    public static Date getDateByTimeStr(Date date, String timeStr) {
        String[] timeArr = timeStr.split(":");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArr[0]));
        cal.set(Calendar.MINUTE, Integer.parseInt(timeArr[1]));
        cal.set(Calendar.SECOND, Integer.parseInt(timeArr[2]));
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static void applyDatesForMap(String startDateYMDStr, String endDateYMDStr, Map<String, Object> conditions) {
        Date startDate = null, endDate = null;
        if (StringUtils.isNotEmpty(startDateYMDStr)) {
            startDate = getDateTime(startDateYMDStr + " 00:00:00");
        }
        if (StringUtils.isNotBlank(endDateYMDStr)) {
            endDate = getDateTime(endDateYMDStr + " 23:59:59");
        }
        if (startDate != null) {
            conditions.put("startTime", forDatetime(startDate));
        }
        if (endDate != null) {
            conditions.put("endTime", forDatetime(endDate));
        }
    }

    /***
     * 获取业务时间所属分钟的开始跟结束时间
     * @param bizTime
     * @return
     * @author jerry
     */
    public static TwoTuple<Date, Date> getMinuteTimeRange(Date bizTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(bizTime);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startTime = calendar.getTime();
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);
        Date endTime = calendar.getTime();
        return TupleUtil.tuple(startTime, endTime);
    }


    /***
     * 获取业务时间所属小时的开始跟结束时间
     * @param bizTime
     * @return
     * @author jerry
     */
    public static TwoTuple<Date, Date> getHourTimeRange(Date bizTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(bizTime);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date startTime = calendar.getTime();
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date endTime = calendar.getTime();
        return TupleUtil.tuple(startTime, endTime);
    }

    /***
     * 获取业务时间所属天的开始跟结束时间
     * @param bizTime
     * @return
     * @author jerry
     */
    public static TwoTuple<Date, Date> getDayTimeRange(Date bizTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(bizTime);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date startTime = calendar.getTime();
        return TupleUtil.tuple(startTime, bizTime);
    }

    /***
     * 获取业务时间所属月份的开始跟结束时间
     * @param bizTime
     * @return
     * @author jerry
     */
    public static TwoTuple<Date, Date> getMonthTimeRange(Date bizTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(bizTime);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date startTime = calendar.getTime();// 当前月第一天
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date endTime = calendar.getTime();// 当前月最后一天
        return TupleUtil.tuple(startTime, endTime);
    }

    /***
     * 获取业务时间所属周的开始跟结束时间（一周开始从周1算）
     * @param bizTime
     * @return
     * @author jerry
     */
    public static TwoTuple<Date, Date> getWeekTimeRange(Date bizTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(bizTime);
        // 获得当前日期是一个星期的第几天
        int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天是星期一
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int first = calendar.getFirstDayOfWeek();
        calendar.add(Calendar.DAY_OF_WEEK, first - day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date startTime = calendar.getTime();// 当前周第一天
        return TupleUtil.tuple(startTime, bizTime);
    }

    /**
     * 线程安全的日期格式化类，用空间换时间
     */
    private static class DateFormatThreadLocal {
        private static ThreadLocal<SimpleDateFormat> THREAD_SAFE_FORMATTER = new ThreadLocal<SimpleDateFormat>() {
            @Override
            protected synchronized SimpleDateFormat initialValue() {
                SimpleDateFormat sd = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
                sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                return sd;
            }
        };

        /**
         * 获取默认的日期格式的 SimpleDateFormat
         *
         * @return 默认格式的 SimpleDateFormat
         */
        public static SimpleDateFormat getFormat() {
            return THREAD_SAFE_FORMATTER.get();
        }

        /**
         * 改变当前线程持有的SimpleDateFormat的日期格式
         *
         * @param pattern
         * @return 指定格式的 SimpleDateFormat
         */
        public static SimpleDateFormat getFormat(String pattern) {
            SimpleDateFormat format = getFormat();
            format.applyPattern(pattern);
            return format;
        }
    }

    /**
     * 返回指定日期加上months月后的日期
     *
     * @param date
     * @param months
     * @return
     */
    public static Date plusMonths(Date date, int months) {
        return changeMonths(date, months);
    }

    /**
     * 返回指定日期改变months月后的日期
     *
     * @param date
     * @param months
     * @return
     */
    private static Date changeMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * 返回指定日期加上months月后的月份最后一天
     *
     * @param date
     * @param month
     */
    public static Date getPlusMonthLastDay(Date date, int month) {
        Date afterDate = plusMonths(date, month);
        return getMonthTimeRange(afterDate).getSecond();
    }

    /**
     * 返回指定日期加上months月后的月份第一天
     *
     * @param date
     * @param month
     */
    public static Date getPlusMonthFirstDay(Date date, int month) {
        Date afterDate = plusMonths(date, month);
        return getMonthTimeRange(afterDate).getFirst();
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse("2017-10-20");
        System.out.println(sdf.format(date1));
    }

}
