/**
 *
 */
package com.lab.manage.util;

import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 功能：时间处理工具类
 * Version 0.1
 */
public class DateUtil {

	public static final long SECOND = 1000L;
	public static final long MINUTE = 60000L;
	public static final long HOUR = 3600000L;
	public static final long DAY = 86400000L;
	public static final long WEEK = 604800000L;
	public static final String YMD = "yyyyMMdd";
	public static final String YMDHMS = "yyyyMMddHHmmss";
	public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DEFAULT_FORMAT_NO_SECOND = "yyyy-MM-dd HH:mm"; //存管4.0 交易记录需要
	public static final String SIMPLE_FORMAT = "yyyy-MM-dd";
	public static final String MMDD_SIMPLE_FORMAT = "MMdd";
	public static final String YYYY_MM_FORMAT = "yyyyMM";
	public static final String YY_MM_DD_FORMAT = "yy/MM/dd";
	public static final String MM_DD_mm_ss_FORMAT = "MM/dd HH:mm";
	public static final long minsInDay = 24L * 60L * 60L * 1000L;
	public static final String YMDtradition = "yyyy年MM月dd日";
	

	/**
	 * 获取某一天的开始时间
	 * @param cal
	 * @return
	 */
	public static Calendar setStartDay(Calendar cal)
	{
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal;
	}

	/**
	 * 获取某一天的结束时间
	 *
	 * @param cal
	 * @return
	 */
	public static Calendar setEndDay(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal;
	}

	/**
	 * 获得昨天的时间
	 *
	 * @return
	 */
	public static Date getYestoday() {
		return truncate(getDateAgo(1), Calendar.DATE);
	}

	/**
	 * 获得前days天的这个时候
	 *
	 * @param days
	 * @return
	 */
	public static Date getDateAgo(int days) {
		return getDateAgo(new Date(), days);
	}

	/**
	 * 获得某时间之前days天的时间
	 *
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getDateAgo(Date date, int days) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.DATE, -days);
		return c.getTime();
	}

	/**
	 * 获得days天后的时间
	 *
	 * @param days
	 * @return
	 */
	public static Date getDateAfter(int days) {
		return getDateAgo(new Date(), -days);
	}

	/**
	 * 获得某个时间之后days天的时间
	 *
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getDateAfter(Date date, int days) {
		return getDateAgo(date, -days);
	}

	/**
	 * 获得本周第一天
	 *
	 * @return
	 */
	public static Date getFirstDayOfThisWeek() {

		Calendar c = new GregorianCalendar();
		c.set(Calendar.DAY_OF_WEEK, 2);
		return truncate(c.getTime(), Calendar.DATE);
	}

	/**
	 * 获得本周最后一天
	 *
	 * @return
	 */
	public static Date getLastDayOfThisWeek() {

		Calendar c = new GregorianCalendar();
		c.set(Calendar.DAY_OF_WEEK, 7);
		c.add(Calendar.DATE, 1);
		return truncate(c.getTime(), Calendar.DATE);
	}

	/**
	 * 获得本月第一天
	 *
	 * @return
	 */
	public static Date getFirstDayOfThisMonth() {

		Calendar c = new GregorianCalendar();
		c.set(Calendar.DAY_OF_MONTH, 1);
		return truncate(c.getTime(), Calendar.DATE);
	}

	/**
	 * 获得本月最后一天
	 *
	 * @return
	 */
	public static Date getLastDayOfThisMonth() {

		Calendar c = new GregorianCalendar();
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DATE, -1);
		c.add(Calendar.MONTH, 1);
		return truncate(c.getTime(), Calendar.DATE);
	}

	/**
	 * 获得上周的最后一天
	 *
	 * @return
	 */
	public static Date getLastDayOfLastWeek() {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.DAY_OF_WEEK, 1);
		return truncate(c.getTime(), Calendar.DATE);
	}

	/**
	 * 获得上周的第一天
	 *
	 * @return
	 */
	public static Date getFirstDayOfLastWeek() {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.DAY_OF_WEEK, 1);
		c.add(Calendar.DATE, -6);
		return truncate(c.getTime(), Calendar.DATE);
	}

	/**
	 * 获得上个月的最后一天
	 *
	 * @return
	 */
	public static Date getLastDayOfLastMonth() {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DATE, -1);
		return truncate(c.getTime(), Calendar.DATE);
	}

	/**
	 * 获得上个月第一天
	 *
	 * @return
	 */
	public static Date getFirstDayOfLastMonth() {
		Calendar c = new GregorianCalendar();
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return truncate(c.getTime(), Calendar.DATE);
	}

	/**
	 * 
	     * @Description 获取当前年份   返回格式： yyyy 
	     * @author  xiayinfeng        
	     * @created 2017年9月27日 下午6:29:05     
	     * @return
	 */
	public static String getSysYear() {
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        return year;
}
	
	/**
	 * 查看两个日期是否是同一天
	 *
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean isSameDate(Date d1, Date d2) {
		return org.apache.commons.lang.time.DateUtils.isSameDay(d1, d2);
	}

	/**
	 * 查看d1是否在d2之后
	 *
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean afterDate(Date d1, Date d2) {
		d1 = org.apache.commons.lang.time.DateUtils.truncate(d1, Calendar.DATE);
		d2 = org.apache.commons.lang.time.DateUtils.truncate(d2, Calendar.DATE);
		return d1.after(d2);
	}

	/**
	 * 查看d1是否在d2之前
	 *
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean beforeDate(Date d1, Date d2) {
		d1 = org.apache.commons.lang.time.DateUtils.truncate(d1, Calendar.DATE);
		d2 = org.apache.commons.lang.time.DateUtils.truncate(d2, Calendar.DATE);
		return d1.before(d2);
	}

	/**
	 * 在两个日期之间
	 *
	 * @param d
	 * @param from
	 * @param to
	 * @return
	 */
	public static boolean isBetweenDate(Date d, Date from, Date to) {
		return (DateUtil.afterDate(d, from) && DateUtil.beforeDate(d, to))
				|| (isSameDate(d, from) || isSameDate(d, to));
	}

	/**
	 * 获得某个日期的零值
	 *
	 * @param date
	 * @return
	 */
	public static Date getDate(Date date) {
		date = (date == null ? new Date() : date);
		return truncate(date, Calendar.DATE);
	}

	/**
	 * 截断日期
	 *
	 * @param d1
	 * @param i
	 *            java.util.Calendar.DATE
	 * @return
	 */
	public static Date truncate(Date d1, int i) {
		return org.apache.commons.lang.time.DateUtils.truncate(d1, i);
	}

	/**
	 * 格式化日期
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		String temp = "";
		if(date != null && StringUtils.isNotBlank(pattern)){
			SimpleDateFormat df = new SimpleDateFormat(pattern, Locale.CHINESE);
			temp = df.format(date);
		}
		return temp;
	}

	/**
	 * 解析时间字符串
	 *
	 * @param dateString
	 * @param format
	 * @return
	 */
	public static Date parse(String dateString, String format) {
		return parse(dateString, format, Locale.CHINESE, TimeZone.getDefault());
	}

	/**
	 * 解析时间字符串
	 *
	 * @param dateString
	 * @param format
	 * @param locale
	 * @param timeZone
	 * @return
	 */
	public static Date parse(String dateString, String format, Locale locale,
							 TimeZone timeZone) {
		SimpleDateFormat formatter = (SimpleDateFormat) DateFormat
				.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		formatter.applyPattern(format);
		formatter.setTimeZone(timeZone);
		Date date = null;
		try {
			date = formatter.parse(dateString);
		} catch (Exception e) {
			date = null;
		}
		return date;
	}

	/**
	 * 获得当前时间的格式
	 *
	 * @param pattern
	 * @return
	 */
	public static String getCurrentTime(String pattern) {
		SimpleDateFormat ilIlI11I1 = new SimpleDateFormat(pattern);
		return ilIlI11I1.format(new Date());
	}

	/**
	 * 通过时间戳得到当前时间
	 *
	 * @param currentTimeMillis
	 * @return
	 */
	public static Date getDateByCurrentTimeMillis(long currentTimeMillis) {
		Timestamp now = new Timestamp(currentTimeMillis);
		return now;
	}

	/**
	 * 获得两个时间的间隔天数
	 * eg:2016-06-15 和 2016-06-18 间隔3天
	 *
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int getIntervalDays(Date begin, Date end) {
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		String d1 = sd.format(begin);
		String d2 = sd.format(end);
		return DateUtil.getIntervalDays(d1, d2);
	}

	/**
	 * 计算两日期之间的天数
	 * @return
	 */
	public final static int getIntervalDays(String begin, String end){
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		Date d2=null;
		try {
			d1 = sd.parse(begin);
			d2 = sd.parse(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long between = (d2.getTime() - d1.getTime()) / 1000;// 除以1000是为了转换成秒
		return (int) between / (24 * 3600);
	}

	/**
	 * 获得两个时间的间隔小时
	 *
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int getIntervalHours(Date begin, Date end) {
		if (begin == null || end == null)
			return 0;
		long between = (end.getTime() - begin.getTime()) / 1000;
		return (int) between / 3600;
	}

	/**
	 * 获得两个时间的间隔分钟
	 *
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int getIntervalMinites(Date begin, Date end) {
		if (begin == null || end == null)
			return 0;
		long between = (end.getTime() - begin.getTime()) / 1000;
		return (int) between / 60;
	}

	/**
	 * 获得两个时间的间隔秒
	 *
	 * @param date
	 * @param date2
	 * @return
	 */
	public static int getIntervalSecond(Date begin, Date end) {

		if (begin == null || end == null)
			return 0;
		long between = (end.getTime() - begin.getTime()) / 1000;
		return (int) between;
	}

	/**
	 * 获得当前距离1970年的秒数
	 *
	 * @return
	 */
	public static int getCurrentTimeSeconds() {

		return (int) (System.currentTimeMillis() / 1000L);
	}

	/**
	 * 获得SQL时间
	 *
	 * @param date
	 * @return
	 */
	public static java.sql.Date getSqlDate(Date date) {
		if (date == null)
			return null;
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 获得SQL时间戳
	 *
	 * @param date
	 * @return
	 */
	public static Timestamp getSqlTimestamp(Date date) {

		if (date == null)
			return null;
		return new Timestamp(date.getTime());
	}

	/**
	 * 功能描述:实现了时间加号和减号 条块: y: year m: month d: date h:hour f: minute s:second
	 *
	 * @param date
	 * @param type
	 * @param timeInterval
	 * @return
	 */
	public static Date computeDate(Date date, char type, int timeInterval) {
		if (date == null) {
			date = new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int Time_year = cal.get(Calendar.YEAR);
		int Time_month = cal.get(Calendar.MONTH);
		int Time_day = cal.get(Calendar.DAY_OF_MONTH);
		int Time_hour = cal.get(Calendar.HOUR_OF_DAY);
		int Time_minute = cal.get(Calendar.MINUTE);
		int Time_second = cal.get(Calendar.SECOND);

		switch (type) {
			case 'y': {
				Time_year = Time_year + timeInterval;
				cal.set(Calendar.YEAR, Time_year);
			}
			break;

			case 'm': {
				Time_month = Time_month + timeInterval;
				cal.set(Calendar.MONTH, Time_month);
			}
			break;

			case 'd': {
				Time_day = Time_day + timeInterval;
				cal.set(Calendar.DAY_OF_MONTH, Time_day);
			}
			break;

			case 'h': {
				Time_hour = Time_hour + timeInterval;
				cal.set(Calendar.HOUR_OF_DAY, Time_hour);
			}
			break;

			case 'f': {
				Time_minute = Time_minute + timeInterval;
				cal.set(Calendar.MINUTE, Time_minute);
			}
			break;

			case 's': {
				Time_second = Time_second + timeInterval;
				cal.set(Calendar.SECOND, Time_second);
			}
			break;
			default:
				break;
		}
		date = cal.getTime();
		return date;
	}

	/**
	 * 在日期上加days天，得到新的日期
	 * @return
	 */
	public final static Date addDaysToDate(Date date,int days){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE,days);
		return c.getTime();
	}

	/**
	 * 在日期上加months月，得到新的日期
	 *
	 * @param date
	 * @param months
	 * @return
	 */
	public final static Date addMonthsToDate(Date date,int months){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH,months);
		return c.getTime();
	}

	/**
	 * 在日期上加months月和日，得到新的日期
	 *
	 * @param date
	 * @param months
	 * @param days
	 * @return
	 */
	public final static Date addMonthsToDate(Date date,int months,int days){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH,months);
		c.add(Calendar.DATE,days);
		//	c.roll(Calendar.DATE, days);
		return c.getTime();
	}

	/**
	 * 获取日期所在月份的最大日期
	 *
	 * @param date
	 * @return
	 */
	public static Date getMaxDateOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 获取日期所在月份的最小日期
	 *
	 * @param date
	 * @return
	 */
	public static Date getMinDateOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	public static String dateToStr(Date date) {
		if (date != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			// Date dBeginDate;
			String dateStr = dateFormat.format(date);
			return dateStr;
		}
		return null;
	}

	public static String dateToString(Date date) {
		if (date != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			// Date dBeginDate;
			String dateStr = dateFormat.format(date);
			return dateStr;
		}
		return null;
	}


	/**
	 * @author dong
	 * 2015-12-16转换为12/16
	 * @param date
	 * @return
	 */
	public static String dateToSimple(Date date) {
		if (date != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			// Date dBeginDate;
			String dateStr = dateFormat.format(date);
			String dateSimple = dateStr.substring(5,7)+"/"+dateStr.substring(8,10);
			return dateSimple;
		}
		return null;
	}


	/**
	 *
	 * 2012-8-7 将字符串转换成日期类(yyyy-MM-dd)
	 *
	 * @param date
	 *            需要转化的日期字符串
	 * @return 转化后的日期
	 */
	public static Date parseDate(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		if ((date == null) || (date.equals(""))) {
			return null;
		} else {
			try {
				return formatter.parse(date);
			} catch (ParseException e) {
				return null;
			}
		}
	}
	
	public static Date parseDateOrTime(String date,String template) {
		if(StringUtils.isEmpty(template)){
			template = DEFAULT_FORMAT;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(template);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		if ((date == null) || (date.equals(""))) {
			return null;
		} else {
			try {
				return formatter.parse(date);
			} catch (ParseException e) {
				return null;
			}
		}
	}

	/**
	 *
	 * @Description 格林威治时间转换为yyyy-MM-dd类型的字符串
	 * @author  xiayinfeng
	 * @created 2016年9月1日 下午9:47:25
	 * @param str
	 * @return
	 */
	public static String cmtTODateStr(String str){
		//String str = "Wed Jun 5 00:00:00 GMT+08:00 2013";//在08与00之间加：
		Date d = new Date(str);
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(d);
		return format;
	}


	/**
	 *
	 * 2012-8-7 根据传入的日期类型格式化日期
	 *
	 * @param date
	 *            需要格式化的日期
	 * @param pattern
	 *            日期格式
	 * @return 格式化后的日期字符串
	 */
	public static String formatDate(Date date, String pattern) {
		if (date == null)
			return "";
		if (pattern == null)
			pattern = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return (sdf.format(date));
	}

	/**
	 *
	 * 取两个时间段重叠的天数。所有时间取00：00：00
	 * 注意，没有判断
	 * @author shuo
	 * @date  2015-5-28下午2:16:46
	 *
	 * @return
	 * @return int
	 * @throws Exception 检索失败
	 */
	public static int getDayCoincidence(Date p1, Date p2, Date c1 , Date c2){
		String timeFormat = "yyyy-MM-dd";
		p1 = parse(format(p1,timeFormat),timeFormat);
		p2 = parse(format(p2,timeFormat),timeFormat);
		c1 = parse(format(c1,timeFormat),timeFormat);
		c2 = parse(format(c2,timeFormat),timeFormat);

		if(p1.compareTo(p2) > 0 || c1.compareTo(c2) > 0){
			throw new RuntimeException("日期输入有错误,开始时间大于结束时间!");
		}

		if(p2.compareTo(c1)<0 || c2.compareTo(p1)<0){
			return 0;
		}
		int re = 0;
		if(p1.compareTo(c1) >= 0){
			if(p2.compareTo(c2) >= 0){
				//c1-----p1-----c2----p2,取p1c2
				re = getIntervalDays(p1, c2);
			}else{
				//c1-----p1-----p2----c2,取p1p2
				re = getIntervalDays(p1, p2);
			}
		}else{
			if(p2.compareTo(c2) >= 0){
				//p1-----c1-----c2----p2,取c1c2
				re = getIntervalDays(c1, c2);
			}else{
				//p1-----c1-----p2----c2,取c1p2
				re = getIntervalDays(c1, p2);
			}
		}
		//当天也算，结果需要+1
		return Math.abs(re) + 1;
	}
	public static Date getSysDate() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 *
	 * 2012-8-7 将传入的日期格式化为yyyy-MM-dd HH:mm:ss格式字符串
	 *
	 * @param date
	 *            需要格式化的日期
	 * @return 格式化后的日期字符串
	 */
	public static String formatDateTime(Date date) {
		return (formatDate(date, "yyyy-MM-dd HH:mm:ss"));
	}

	/**
	 *
	 * 2012-8-7 返回格式化为yyyy-MM-dd HH:mm:ss的字符串的当前日期
	 *
	 * @return 格式化后的日期
	 */
	public static Date getDateTime() {
		return parseDateTime(formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
	}
	/**
	 * 比较两个日期的大小
	 * @param date1
	 * @param date2
	 * @return date1>date2 返回1 date1<date2 返回-1 date1=date2 返回0
	 */
	public static int compareDate(String date1, String date2) {
		DateFormat df = new SimpleDateFormat(DEFAULT_FORMAT);
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			if (dt1.getTime() > dt2.getTime()) {
				//System.out.println("dt1 在dt2前");
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				//System.out.println("dt1在dt2后");
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}
	/**
	 * 比较两个日期的大小
	 * @param date1
	 * @param date2
	 * @return date1>date2 返回1 date1<date2 返回-1 date1=date2 返回0
	 */
	public static int compareDateNew(Date date1, Date date2) {
		try {
			if (date1.getTime() > date2.getTime()) {
				//System.out.println("dt1 在dt2前");
				return 1;
			} else if (date1.getTime() < date2.getTime()) {
				//System.out.println("dt1在dt2后");
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}
	/**
	 *
	 * 2012-8-7 将字符串转换成日期类(yyyy-MM-dd HH:mm:ss)
	 *
	 * @param datetime
	 *            需要转化的日期字符串
	 * @return 转化后的日期
	 */
	public static Date parseDateTime(String datetime) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if ((datetime == null) || (datetime.equals(""))) {
			return null;
		} else {
			try {
				return formatter.parse(datetime);
			} catch (ParseException e) {
				return parseDate(datetime);
			}
		}
	}

	/**
	 * 比较两个时间
	 * @param date1
	 * @param date2
	 * @return date1>date2 返回1 date1=date2 返回0 date1<date2 返回-1
	 */
	public static int compareDateTime(String date1, String date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			if (dt1.getTime() > dt2.getTime()) {
				//System.out.println("dt1 在dt2前");
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				//System.out.println("dt1在dt2后");
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}


	public static String cal(int second) {
		int h = 0;
		int d = 0;
		int s = 0;
		int temp = second % 3600;
		if (second > 3600) {
			h = second / 3600;
			if (temp != 0) {
				if (temp > 60) {
					d = temp / 60;
					if (temp % 60 != 0) {
						s = temp % 60;
					}
				} else {
					s = temp;
				}
			}
		} else {
			d = second / 60;
			if (second % 60 != 0) {
				s = second % 60;
			}
		}
		if (h == 0 && d == 0) {
			return s + "秒";
		}
		if (h == 0 && d != 0) {
			return d + "分" + s + "秒";
		}
		return h + "时" + d + "分" + s + "秒";
	}

	/**
	 * 获取身份证中的生日（月,日）
	 * @param ID
	 * @return
	 *
	 */
	public static String idSubstring(String ID) {
		String birthday = "";
		if(null != ID){
			SimpleDateFormat dateFormat = new SimpleDateFormat("MMdd");
			dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			if(ID.length() == 15){//15位老身份证号
				birthday = ID.substring(8, 12);
			} else if(ID.length() == 18){//18位二代身份证号
				birthday = ID.substring(10, 14);
			}
		}
		return birthday;
	}

	/**
	 * 
	     * @Description 通过用户身份证号判断是否今天生日
	     * @author  xiayinfeng         
	     * @created 2017年9月18日 上午10:35:45     
	     * @param ID
	     * @return
	 */
	public static Boolean todayBirthdayTemp(String IDCard) {
		if(StringUtils.isEmpty(IDCard)){
			return false;
		}
		Date today = new Date();//获取当前时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMdd");
		String format = dateFormat.format(today);//将当前时间转换格式，用于下面比较使用
		String idStringToDate = DateUtil.idSubstring(IDCard);//当前用户的身份证中的生日
		if(idStringToDate.equals(format)){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	     * @Description 是否今天生日
	     * @author  xiayinfeng         
	     * @created 2017年10月10日 下午4:01:18     
	     * @param birthday
	     * @return
	 */
	public static Boolean todayBirthday(String birthday) {
		if(StringUtils.isEmpty(birthday)){
			return false;
		}
		Date today = new Date();//获取当前时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMdd");
		String format = dateFormat.format(today);//将当前时间转换格式，用于下面比较使用
		if(birthday.equals(format)){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	     * @Description 是否今天以后:如果true则表示过期，否则未过期
	     * @author  xiayinfeng         
	     * @created 2017年10月10日 下午4:01:36     
	     * @param birthday
	     * @return
	 */
	public static Boolean isTodayAfter(String birthday) {
		if(StringUtils.isEmpty(birthday)){
			return false;
		}
		Date today = new Date();//获取当前时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMdd");
		String format = dateFormat.format(today);//将当前时间转换格式，用于下面比较使用
		
		if(birthday.compareTo(format)<0){//生日过期了
			return true;
		}
		return false;
	}
	
	
	/**
	 * 获得某时间小时
	 * @param date
	 * @return
	 */
	public static int getHour(Date date){
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获得某时间分钟
	 * @param date
	 * @return
	 */
	public static int getMinute(Date date){
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		return c.get(Calendar.MINUTE);
	}


	public static Date addDateTime(String type, Date date, int val){
		if(type==null || "".equals(type)){
			return null;
		}
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(date);
		if("YEAR".equals(type)){
			rightNow.add(Calendar.YEAR, val);
		}else if("MONTH".equals(type)){
			rightNow.add(Calendar.MONTH, val);
		}else if("DAY".equals(type)){
			rightNow.add(Calendar.DAY_OF_YEAR, val);
		}
		return rightNow.getTime();
	}
	/**
	 *
	 * @Description 获取当前时间i分钟之前的时间
	 * @param i
	 * @return
	 */
	public static String getTimeBefore(int i){
		long l = 5*60*1000;
		long time = new Date().getTime();
		long temp = time - l;
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
		calendar.setTimeInMillis(temp);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = sdf.format(calendar.getTime());
		return format;
	}

	/**
	 * @author juncai.dong
	 * 申请解绑银行卡成功时发送短信时间处理
	 * @param date
	 * @return
	 */
	public static String  getMonthDayHourMin(Date date){
		String time = formatDateTime(date);
		if(null != time){
			String str = time.substring(5,7)+"月"+time.substring(8,10)+"日"+time.substring(11,13)+"时"+time.substring(14,16)+"分";
			// String str = time.substring(5,7)+"月"+time.substring(8,10)+"日"+time.substring(11,16);
			return str;
		}else{
			return null;
		}
	}


	/**
	 * @author dong
	 * 2015-12-16 14:00:23转换为2015/12/16
	 * @param date
	 * @return
	 */
	public static String dateToNormal(Date date) {
		if (date != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			// Date dBeginDate;
			String dateStr = dateFormat.format(date);
			String dateSimple = dateStr.substring(0,4)+"/"+dateStr.substring(5,7)+"/"+dateStr.substring(8,10);
			return dateSimple;
		}
		return null;
	}

	/**
	 * @Author Chengcheng
	 * @Description :  2017-05-05 转换为 0505
	 * @Date 2017/5/5 上午10:02
	 */
	public static String getBirthday(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd");
		String dateStr = simpleDateFormat.format(date);
		String[] strings = dateStr.split("/");
		StringBuilder builder = new StringBuilder();
		for (String split :strings) {
			builder.append(split);
		}
		return builder.toString();
	}

	
	/**
	 * 
	     * @Description 下一年
	     * @author  xiayinfeng         
	     * @created 2017年9月15日 上午10:26:39     
	     * @param date
	     * @param parten
	     * @return
	 */
	public static String nextYear(Date date,String parten,Integer days){
		if(StringUtils.isEmpty(parten)){
			parten = DEFAULT_FORMAT;
		}
		SimpleDateFormat format = new SimpleDateFormat(parten);
		if(null == date){
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		if(null == days){
			days = 1;
		}
		//明年
        c.setTime(date);
        c.add(Calendar.YEAR, days);
        Date formartDate = c.getTime();
        String nextYear = format.format(formartDate);
        return nextYear;
	}

	/**
	 * 
	     * @Description 是否同一年
	     * @author  xiayinfeng         
	     * @created 2017年9月27日 下午6:37:45     
	     * @param date
	     * @param string
	     * @return
	 */
	public static Boolean isSameYear(Date dateA,Date dateB) {
		//formatDate(new Date(), "yyyy");
		String pattern = "yyyy";
		if(null ==  dateA || null ==  dateB){
			return false;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String formatA = sdf.format(dateA);
		String formatB = sdf.format(dateB);
		if(formatA.equals(formatB)){
			return true;	
		}
		return false;
	}
	
	/**
     * @Description 判断是否在领取时间和过期时间之间:即再过期日期之前
     *              用于判断特权非首次完成时，是否可领取和可再次完成
     * @author  xiayinfeng         
     * @created 2017年10月11日 下午7:27:44     
     * @return
	 */
	public static Boolean InBetweenYear(Date now,Date expireDate) {
		if(null ==  now || null ==  expireDate){
			return false;
		}
		if(now.before(expireDate)){
			return true;	
		}
		return false;
	}

	/**
	 * 根据日期计算是星期几
	 * @param pTime 2018-05-31
	 * @return
	 */
	public static int getWeekByDate(String pTime) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(format.parse(pTime));
		}catch (Exception e){
			e.getMessage();
		}
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	/**
	 * 根据身份证计算年龄
	 * @param idCard
	 * @return
	 * @throws Exception
	 */
	public static int getAge(String  idCard) throws Exception {
		String  birth = null;
		if(idCard.length()==15){
			String str=idCard.substring(6,12);
			birth="19"+str;
		}else if(idCard.length()==18){
			birth=idCard.substring(6,14);
		}
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date birthDay =  df.parse(birth);
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthDay)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		int age = yearNow - yearBirth;
		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) age--;
			}else{
				age--;
			}
		}
		return age;
	}

}
