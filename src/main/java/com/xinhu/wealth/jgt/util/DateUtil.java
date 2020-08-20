package com.xinhu.wealth.jgt.util;

import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {
    public static String getTime(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
//        calendar.add(Calendar.DAY_OF_MONTH, +3);
        date = calendar.getTime();
        return  sdf.format(date);
    }

    /**
     * 当前日期减去天数后的日期
     * @param num 为减去的天数
     * @return
     */
    public static Date queryAgoTime(int num){
        Date date = new Date();//获取当前时间    
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
//        calendar.add(Calendar.YEAR, -1);//当前时间减去一年，即一年前的时间    
//        calendar.add(Calendar.MONTH, -1);//当前时间前去一个月，即一个月前的时间    
        calendar.add(Calendar.DATE, -num);//当前时间减去的天数
        return calendar.getTime();//获取减去的天数后的时间
    }

    public static String getTime(String source){
        SimpleDateFormat simple = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simple.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  sdf.format(date);
    }

    public static String getAgoTime(String source){
        SimpleDateFormat simple = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simple.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,-7);//当前时间减去7天
        return  sdf.format(calendar.getTime());
    }

    public static String conversionTime(long time){
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
        String result = sim.format(time);
        return result;
    }

    public static String getTimestamp(){
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmmss");
        String result = sim.format(new Date());
        return result;
    }

    /**
     * 获取今天的最后时间 23:59:59
     */
    public static long getLastTimeOfToday() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTimeInMillis();
    }

    public static long getRemainingTime(){
        return (getLastTimeOfToday()-new Date().getTime())/1000;
    }

    public static String getTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
    }

    public static void main(String args[]){
        Map map = new HashMap();
        map.put("aaaa","aa");
        map.put("bbbb","bb");
        Object json = JSONObject.toJSON(map);
        System.out.println(json);
    }


}
