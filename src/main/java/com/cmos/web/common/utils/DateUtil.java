package com.cmos.web.common.utils;

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
  private static Log log = LogFactory.getLog(DateUtil.class);
  public static String YYYY_MM = "yyyy-MM";

  public static String YYYY_MM_DD = "yyyy-MM-dd";
  public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
  public static String YYYY_MM_DD_HH_MM_SSS = "yyyyMMddHHmmsss";
  public static String YYYYMM = "yyyyMM";

  public static String YYYYMMDD = "yyyyMMdd";

  private static SimpleDateFormat formatdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private static SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd");

  private static String defaultDatePattern = null;

  public static Date addMonth(String startdate, int nmonth)
  {
    try
    {
      SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");

      Calendar cd = Calendar.getInstance();

      cd.setTime(sdf1.parse(startdate));
      cd.add(2, nmonth);
      return cd.getTime(); } catch (Exception e) {
    }
    return null;
  }

  public static Date addMonth(Date date, int nmonth)
  {
    try {
      Calendar cd = Calendar.getInstance();
      cd.add(2, nmonth);
      return cd.getTime(); } catch (Exception e) {
    }
    return null;
  }

  public static Date addDay(Date current, int days)
  {
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(current);
    calendar.add(5, days);
    return calendar.getTime();
  }

  public static Date addHour(Date current, int hour)
  {
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(current);
    calendar.add(10, hour);
    return calendar.getTime();
  }

  public static Date addMinute(Date current, int minute)
  {
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(current);
    calendar.add(12, minute);
    return calendar.getTime();
  }

  public static Date addSecond(Date current, int second) {
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(current);
    calendar.add(13, second);
    return calendar.getTime();
  }

  public static int compare_date(String DATE1, String DATE2)
  {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    try {
      Date dt1 = df.parse(DATE1);
      Date dt2 = df.parse(DATE2);
      if (dt1.getTime() > dt2.getTime())
        return 1;
      if (dt1.getTime() < dt2.getTime()) {
        return -1;
      }
      return 0;
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
    return 0;
  }

  public static Date diffDate(Date date, int day)
  {
    Calendar c = Calendar.getInstance();
    c.setTimeInMillis(getMillis(date) - day * 24L * 3600L * 1000L);
    return c.getTime();
  }

  public static int diffDateReDay(Date date, Date date1)
  {
    return (int)((getMillis(date) - getMillis(date1)) / 86400000L);
  }

  public static double diffDateReDayDouble(Date date, Date date1, int scale)
  {
    return div(getMillis(date) - getMillis(date1), 86400000.0D, 
      scale);
  }

  public static int diffDateReHour(Date date, Date date1)
  {
    return (int)((getMillis(date) - getMillis(date1)) / 3600000L);
  }

  public static double diffDateReHourDouble(Date date, Date date1, int scale)
  {
    return div(getMillis(date) - getMillis(date1), 3600000.0D, scale);
  }

  public static double div(double v1, double v2, int scale)
  {
    if (scale < 0) {
      throw new IllegalArgumentException(
        "The scale must be a positive integer or zero");
    }
    BigDecimal b1 = new BigDecimal(Double.toString(v1));
    BigDecimal b2 = new BigDecimal(Double.toString(v2));
    return b1.divide(b2, scale, 4).doubleValue();
  }

  public static Date getCurrentDate()
  {
    SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
    try {
      return sdf.parse(getCurrentDateString(null));
    } catch (ParseException e) {
      log.error("日期转换异常getCurrentDate()", e);
      e.printStackTrace();
    }return new Date();
  }

  public static Date getCurrentDate(String parton)
  {
    if ((parton == null) || (parton.trim().equals(""))) {
      parton = YYYY_MM_DD_HH_MM_SS;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(parton);
    try {
      return sdf.parse(getCurrentDateString(null));
    } catch (ParseException e) {
      log.error("日期转换异常getCurrentDate(String parton)", e);
      e.printStackTrace();
    }return new Date();
  }

  public static String getCurrentDateString(String parton)
  {
    if ((parton == null) || ("".equals(parton.trim()))) {
      parton = YYYY_MM_DD_HH_MM_SS;
    }
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat(parton);
    sdf.setTimeZone(new SimpleTimeZone(28800000, "Asia/Shanghai"));

    return sdf.format(date);
  }


  public static Date getDateAmountDays(Date date, int amount)
  {
    Calendar cal = new GregorianCalendar();
    cal.setTime(date);
    cal.add(5, amount);
    return cal.getTime();
  }

  public static String getFirstDayOfNowMonth()
  {
    String str = "";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    Calendar lastDate = Calendar.getInstance();
    lastDate.set(5, 1);
    str = sdf.format(lastDate.getTime());
    return str;
  }

  public static String getGiveDateFirstDay(String startdate)
  {
    try
    {
      String str = "";
      SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

      Calendar cd = Calendar.getInstance();
      cd.setTime(sdf1.parse(startdate));
      cd.set(5, 1);
      str = sdf.format(cd.getTime());
      return str; } catch (Exception e) {
    }
    return null;
  }

  public static String getLastDayOfNowMonth()
  {
    String str = "";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    Calendar lastDate = Calendar.getInstance();
    lastDate.set(5, 1);
    lastDate.add(2, 1);
    lastDate.add(5, -1);

    str = sdf.format(lastDate.getTime());
    return str;
  }

  public static long getMillis(Date date)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    return c.getTimeInMillis();
  }

  public static String getPreviousDayYesterdayDate()
  {
    Calendar cld = Calendar.getInstance();
    cld.set(5, cld.get(5) - 1);
    Date monthstart = cld.getTime();
    return toStr(monthstart, YYYYMMDD);
  }

  public static String getPreviousDayYesterdayDate(String pattern)
  {
    Calendar cld = Calendar.getInstance();
    cld.set(5, cld.get(5) - 1);
    Date monthstart = cld.getTime();
    return toStr(monthstart, pattern);
  }

  public static String getPreviousMonthEnd()
  {
    String str = "";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    Calendar lastDate = Calendar.getInstance();
    lastDate.add(2, -1);
    lastDate.set(5, 1);
    lastDate.roll(5, -1);
    str = sdf.format(lastDate.getTime());
    return str;
  }

  public static String getPreviousMonthFirst()
  {
    String str = "";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    Calendar lastDate = Calendar.getInstance();
    lastDate.set(5, 1);
    lastDate.add(2, -1);

    str = sdf.format(lastDate.getTime());
    return str;
  }

  public static Date stringToDate(String dateStr, String pattern)
    throws Exception
  {
    Date date = new SimpleDateFormat(pattern).parse(dateStr);

    return date;
  }

  public static Date toDate(String date, String pattern)
  {
	 Date date2;
    if (isEmpty(pattern))
      pattern = YYYY_MM_DD_HH_MM_SS;
    try
    {
      
      if ((date != null) && (!date.equals(""))) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        date2 = sdf.parse(date);
      } else {
        date2 = toLocalDate(new Date(), pattern);
      }
    }
    catch (ParseException e)
    {
      log.error("日期转换异常toDate(String date, String pattern) ", e);
      e.printStackTrace();
      return new Date();
    }
    return date2;
  }

  public static Date toLocalDate(Date date, String pattern)
  {
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    TimeZone zone = new SimpleTimeZone(28800000, "Asia/Shanghai");
    sdf.setTimeZone(zone);
    String sdate = sdf.format(date);

    SimpleDateFormat sdf2 = new SimpleDateFormat(pattern);
    try {
      return sdf2.parse(sdate);
    } catch (ParseException e) {
      log.error("日期转换异常toLocalDate(Date date, String pattern) ", e);
      e.printStackTrace();
    }return new Date();
  }

  public static String toStr(Date date, String pattern)
  {
    String date2 = "";

    if (isEmpty(pattern)) {
      pattern = YYYY_MM_DD_HH_MM_SS;
    }

    if ((date != null) && (!date.equals(""))) {
      SimpleDateFormat sdf = new SimpleDateFormat(pattern);
      date2 = sdf.format(date);
    }

    return date2;
  }

  public static Date getDate(String date)
  {
    try
    {
      return formatdate.parse(date);
    }
    catch (ParseException e) {
      e.printStackTrace();
    }

    return null;
  }

  public static Date getDateTime(String datetime)
  {
    try
    {
      return formatdatetime.parse(datetime);
    }
    catch (ParseException e) {
      e.printStackTrace();
    }

    return null;
  }

  public static String getDateTimeStr(Date date)
  {
    return formatdatetime.format(date);
  }

  public static String getDateStr(Date date)
  {
    return formatdate.format(date);
  }

  public static Date getSystemTime()
  {
    Calendar now = Calendar.getInstance();
    return now.getTime();
  }

  public static String getDatePattern()
  {
    Locale locale = LocaleContextHolder.getLocale();
    try
    {
      defaultDatePattern = ResourceBundle.getBundle("yyyy-MM-dd", 
        locale).getString("date.format");
    } catch (MissingResourceException mse) {
      defaultDatePattern = "yyyy-MM-dd";
    }

    return defaultDatePattern;
  }

  public static String getDate()
  {
    Date aDate = new Date();

    SimpleDateFormat df = null;
    String returnValue = "";

    if (aDate != null) {
      df = new SimpleDateFormat(getDatePattern());
      returnValue = df.format(aDate);
    }

    return returnValue;
  }

  public static Date convertStringToDate(String aMask, String strDate)
  {
    SimpleDateFormat df = null;
    Date date = null;
    df = new SimpleDateFormat(aMask);
    try
    {
      date = df.parse(strDate);
    } catch (ParseException pe) {
      pe.printStackTrace();
    }

    return date;
  }

  public static Date getDateTime() {
    Date date = new Date();

    Date now = null;

    DateFormat d2 = DateFormat.getDateTimeInstance();

    String str2 = d2.format(date);

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try
    {
      now = df.parse(str2);
    }
    catch (ParseException e) {
      e.printStackTrace();
    }

    return now;
  }

  public static Date getDateTimeByReg(String strdate)
    throws Exception
  {
    Date now = null;

    if ((!strdate.equals("")) && (strdate != null)) {
      String str2 = strdate;

      SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
      try
      {
        now = df.parse(str2);
      }
      catch (ParseException e) {
        e.printStackTrace();
      }
    } else {
      throw new Exception();
    }

    return now;
  }

  public static String getDateTimeByDate(Date date)
  {
    String sdate = "";

    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

    if (date != null) {
      sdate = df.format(date);
    }
    return sdate;
  }

  public static String format(Date date, String pattern)
  {
    if (date == null)
      return "";
    SimpleDateFormat formatter = new SimpleDateFormat(pattern);
    return formatter.format(date);
  }

  public static Date addDays(Date target, int days)
  {
    long msPerDay = 86400000L;
    long msTarget = target.getTime();
    long msSum = msTarget + msPerDay * days;
    Date result = new Date();
    result.setTime(msSum);
    return result;
  }

  public static Date beforeDays(Date target, int days)
  {
    long msPerDay = 86400000L;
    long msTarget = target.getTime();
    long msSum = msTarget - msPerDay * days;
    Date result = new Date();
    result.setTime(msSum);
    return result;
  }

  public static Date getFirstDateOfMonth(Date date)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(5, 1);
    calendar.set(11, 0);
    calendar.set(12, 0);
    calendar.set(13, 0);
    return calendar.getTime();
  }

  public static Date getLastlyDateOfMonth(Date date)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    int month = calendar.get(2);
    calendar.set(2, month + 1);
    calendar.set(5, 1);
    calendar.set(11, 23);
    calendar.set(12, 59);
    calendar.set(13, 59);
    calendar.add(5, -1);
    return calendar.getTime();
  }

  public static Date getFirstDateOfNextMonth(Date date)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    int month = calendar.get(2);
    calendar.set(2, month + 1);
    calendar.set(5, 1);
    calendar.set(11, 0);
    calendar.set(12, 0);
    calendar.set(13, 0);
    return calendar.getTime();
  }

  public static String getDatePath()
  {
    Calendar calendar = Calendar.getInstance();

    String path = "";
    int year = calendar.get(1);
    path = String.valueOf(year);
    int month = calendar.get(2) + 1;
    if (month < 10)
      path = path + File.separator + "0" + month;
    else {
      path = year + File.separator + month;
    }
    int day = calendar.get(5);
    if (day < 10) {
      return path + File.separator + "0" + day + File.separator;
    }
    return path + File.separator + day + File.separator;
  }
  
  public static boolean isEmpty(String str)
  {
    return (str == null) || (str.trim().length() <= 0);
  }
  
  @Deprecated
  public static int getCurrentYear()
  {
    return getCurrentDate().getYear() + 1900;
  }
  
}