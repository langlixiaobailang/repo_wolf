package com.cmos.web.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间格式类
 * @author hy 2018年1月4日10:10:58
 *
 */
public class DateUtil
{
  private static Log logger = LogFactory.getLog(DateUtil.class);

  /** 默认格式 */
  public  static final String defaultFormat = "yyyy-MM-dd HH:mm:ss";

  /**
   * 获取当前日期
   * @return
   */
  public static final String getCurrentDate(){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return sdf.format(new Date());
  }
  /**
   * 获取当前日期（毫秒）
   * @return
   */
  public static final String getCurrentDate2(){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsssSSS");
    return sdf.format(new Date());
  }

  /**
   * 获取没有时分秒的当前日期
   * @return
   */
  public static final String getSpecCurrentDate(){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    return sdf.format(new Date());
  }

  /**
   * 获取指定日期所对应最近的周一
   *
   * @param date
   * @return
   */
  public static String getWeekBegin(Date date) {
    return getWeekByDate(date).get("weekBegin");
  }

  /**
   * 获取指定日期所对应最近的周日
   *
   * @param date
   * @return
   */
  public static String getWeekEnd(Date date) {
    return getWeekByDate(date).get("weekEnd");
  }

  /**
   * 获取指定日期所对应最近的周一、周日
   * @param time
   * @return
   */
  public static Map<String, String> getWeekByDate(Date time) {
    Map<String, String> map = new HashMap<>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
    Calendar cal = Calendar.getInstance();
    cal.setTime(time);
    // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
    int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
    if (1 == dayWeek) {
      cal.add(Calendar.DAY_OF_MONTH, -1);
    }
    cal.setFirstDayOfWeek(Calendar.MONDAY);
    int day = cal.get(Calendar.DAY_OF_WEEK);
    cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
    String weekBegin = sdf.format(cal.getTime());
    map.put("weekBegin", weekBegin);
    System.out.println("所在周星期一的日期：" + weekBegin);
    cal.add(Calendar.DATE, 6);
    String weekEnd = sdf.format(cal.getTime());
    map.put("weekEnd", weekEnd);
    System.out.println("所在周星期日的日期：" + weekEnd);
    return map;
  }

  /**
   * 获取指定月的月初和月末
   * @param yearMonth
   * @return
   */
  public static Map<String, Date> getMonthFirstAndEnd(String yearMonth) {
    Map<String, Date> map = new HashMap<>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM"); // 设置时间格式
    Date date;
    try {
      date = sdf.parse(yearMonth);
    } catch (ParseException e) {
      return map;
    }
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    Date firstDayOfMonth = calendar.getTime();
    map.put("monthBegin", firstDayOfMonth);
    calendar.add(Calendar.MONTH, 1);
    calendar.add(Calendar.DAY_OF_MONTH, -1);
    Date lastDayOfMonth = calendar.getTime();
    map.put("monthEnd", lastDayOfMonth);
    return map;
  }

  /**
   * 获取指定月份月初所在的周一 月末所在的周日
   * @param yearMonth
   * @return
   */
  public static Map<String, Date> getMonthWeekFirstAndEnd(String yearMonth) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
    Map<String, Date> monthMap = getMonthFirstAndEnd(yearMonth);
    String monthBegin = getWeekBegin(monthMap.get("monthBegin"));
    String monthEnd = getWeekEnd(monthMap.get("monthEnd"));
    try {
      monthMap.put("monthBegin",sdf.parse(monthBegin));
      monthMap.put("monthEnd",sdf.parse(monthEnd));
    } catch (ParseException e) {
      logger.error("getMonthWeekFirstAndEnd()日期转换出错" + e.getMessage(), e);
      return monthMap;
    }
    return monthMap;
  }
  /**
   * 字符串转换日期格式
   * @param date
   * @return
   */
  public static final Date stringToDate(String date){
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      return sdf.parse(date);
    }catch (Exception e){
      logger.error("stringToDate()日期转换出错" + e.getMessage(), e);
      return null;
    }
  }
  /**
   * 字符串转换日期格式
   * @param date
   * @param format 日期格式 列如(yyyy-MM-dd,yyyy-MM-dd HH:mm:ss 默认是yyyy-MM-dd)
   * @return
   */
  public static final Date stringToDate(String date, String format){
    try {
      String newFormat = format;
      if(StringUtils.isNotBlank(format)){
        newFormat = defaultFormat;
      }
      SimpleDateFormat sdf = new SimpleDateFormat(newFormat);
      return sdf.parse(date);
    }catch (Exception e){
      logger.error("stringToDate()日期转换出错" + e.getMessage(), e);
      return null;
    }
  }

  /**
   * 日期转换成指定格式的字符串
   * @param date
   * @return
   */
  public static final String dateToString(Date date){
    try {
      SimpleDateFormat sdf = new SimpleDateFormat(defaultFormat);
      return sdf.format(date);
    }catch (Exception e){
      logger.error("dateToString()日期转换出错" + e.getMessage(), e);
      return null;
    }
  }
  /**
   * 日期转换成指定格式的字符串
   * @param date
   * @param format 日期格式 列如(yyyy-MM-dd,yyyy-MM-dd HH:mm:ss 默认是yyyy-MM-dd)
   * @return
   */
  public static final String dateToString(Date date, String format){
    try {
      String newFormat = format;
      if(StringUtils.isNotBlank(format)){
        newFormat = defaultFormat;
      }
      SimpleDateFormat sdf = new SimpleDateFormat(newFormat);
      return sdf.format(date);
    }catch (Exception e){
      logger.error("dateToString()日期转换出错" + e.getMessage(), e);
      return null;
    }
  }
  
}