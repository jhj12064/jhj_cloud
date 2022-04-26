package com.jhj.utils;



import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class DateUtil extends org.apache.commons.lang3.time.DateUtils{

    private final static String time_style_date = "yyyy-MM-dd";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM",
    };

    public static String styleDate(Timestamp t1){
        SimpleDateFormat sdf = new SimpleDateFormat(time_style_date);
        return sdf.format(t1);
    }

    public static String styleDate(Timestamp t1,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(t1);
    }

    public static String styleDate(Date t1,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(t1);
    }

    public static Timestamp currentMonfirstDay(){
        LocalDate today = LocalDate.now();
        //本月的第一天
        LocalDate firstday = LocalDate.of(today.getYear(),today.getMonth(),1);
        return Timestamp.valueOf(firstday.atStartOfDay());
    }

    public static Timestamp currentMonlastDay(){
        LocalDate today = LocalDate.now();

        LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfMonth());
        return Timestamp.valueOf(lastDay.atStartOfDay());
    }

    /**
     * 对于t1时间的未来i天
     * @param t1
     * @param i
     * @return
     */
    public static  Timestamp nextDay(Timestamp t1,int i){
        LocalDateTime localDateTime = t1.toLocalDateTime();
        LocalDateTime nextDay = localDateTime.with(TemporalAdjusters.ofDateAdjuster(date -> date.plusDays(i)));
        return Timestamp.valueOf(nextDay);
    }

    public static Timestamp beforeweekDay(){
        LocalDateTime today = LocalDateTime.now();
        //七天前
        LocalDateTime  beforeWeek = today.minusDays(7);
        return Timestamp.valueOf(beforeWeek);
    }

    public static Timestamp now(){
        return Timestamp.valueOf(LocalDateTime.now());
    }


    public static int nowDayOfMon(){
        LocalDate today = LocalDate.now();
        return  today.getDayOfMonth();
    }

    /**
     * 判断 此时此刻是否还在几天后的时间里
     * @param expire  某一时间
     * @param dt  几天后
     * @return
     */
    public static boolean nowBetweenExpire(long expire, int dt){
        long now = System.currentTimeMillis();
        long dtTimes = expire + dt * 24 * 60 * 60 * 1000;// 几天后的时间
        return now <= dtTimes;
    }

    public static boolean nowBetweenExpire(long expire, long dt){
        long now = System.currentTimeMillis();
        long dtTimes = expire + dt ;//
        return now <= dtTimes;
    }

    public static boolean nowBetweenExpire(long expire){
        long now = LocalDateTime.now().getSecond();
        return now <= expire;
    }

    public static Timestamp nowAddDt(int dt){
        return Timestamp.valueOf(LocalDate.now().plusDays(dt).atStartOfDay());
    }

    public static Date times2Date(long times){
        if(times>>1000 == 0){//  10位
            times *= 1000;
        }
        return new Date(times);
    }

    public static long nowAddSecond(long second){
        return LocalDateTime.now().plusSeconds(second).toEpochSecond(ZoneOffset.of("+8"));
    }

    public static Timestamp longTime2Date(long time){
        return new Timestamp(time);
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 日期加几天天
     *
     * @param date
     * @return
     */
    public static Date dateAdd(Date date, int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        //把日期往后增加一天,整数  往后推,负数往前移动
        calendar.add(Calendar.DATE, day);
        //这个时间就是日期往后推一天的结果
        date = calendar.getTime();
        return date;
    }

    /**
     * 根据日期返回当月所有天数
     *
     * @param date
     * @return
     */
    public static List<Date> getAllTheDateOfTheMonth(Date date) {
        List<Date> list = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        int month = cal.get(Calendar.MONTH);
        while(cal.get(Calendar.MONTH) == month){
            list.add(cal.getTime());
            cal.add(Calendar.DATE, 1);
        }
        return list;
    }

    /**
     * 根据月份返回当月所有天数
     *
     * @param date 2021-12
     * @return [2021-12-01, 2021-12-02, 2021-12-03.....2021-12-31]
     */
    public static List<String> getAllDateStrByMonth(String date) {
        List<String> list = new ArrayList<>(50);
        Calendar cal = Calendar.getInstance();
        cal.setTime(parseDate(date));
        cal.set(Calendar.DATE, 1);
        int month = cal.get(Calendar.MONTH);
        while(cal.get(Calendar.MONTH) == month){
            list.add(styleDate(cal.getTime(),time_style_date));
            cal.add(Calendar.DATE, 1);
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(getAllDateStrByMonth("2021-12"));
    }
}



