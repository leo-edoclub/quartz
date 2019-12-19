package com.css.cloud.quartz.zhjc.jsq.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * 
 * <p>
 * Title: EBLUE
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * 
 * 
 * @author wangxiaochen
 * @version 1.0.1
 */
public class DateUtil {

	static public String getDateStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	static public Date parseDate(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(s);
	}

	static public String getLongDateStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	static public Date parseLongDate(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.parse(s);
	}

	static public Date addDays(Date date, int days) {
		Calendar cal = getCalendar(date);
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
	}

	// 将日期去除时分秒
	public static Date parseDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (cal.get(Calendar.HOUR_OF_DAY) != 0 || cal.get(Calendar.MINUTE) != 0 || cal.get(Calendar.SECOND) != 0
				|| cal.get(Calendar.MILLISECOND) != 0) {
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
		}
		return cal.getTime();
	}

	// 获取相隔天数
	public static int getDiffDays(Date begin, Date end) {
		int rtn = 0;
		if (begin != null && end != null) {
			long lngMinMilSec = begin.getTime();
			long lngMaxMilSec = end.getTime();
			rtn = (int) ((lngMaxMilSec - lngMinMilSec) / 86400000);
		}
		return rtn;
	}

	// 获取相隔毫秒数(忽略日期,只计算时间,按工作时间9点至17点计算)
	public static long getDiffTimesIgnoreDate(Date begin, Date end) {
		long rtn = 0;
		if (begin != null && end != null) {
			// Date对象的getTime方法获取的是自1970年8点以来的毫秒数，
			// 这里补上8个小时的毫秒数28800000，计算忽略日期的当天毫秒数
			long lngMinMilSec = (begin.getTime() + 28800000) % 86400000;
			if (lngMinMilSec < 32400000) { // 9点的毫秒数为32400000
				lngMinMilSec = 32400000;
			} else if (lngMinMilSec > 61200000) { // 17点的毫秒数为61200000
				lngMinMilSec = 61200000;
			}
			long lngMaxMilSec = (end.getTime() + 28800000) % 86400000;
			if (lngMaxMilSec < 32400000) {
				lngMaxMilSec = 32400000;
			} else if (lngMaxMilSec > 61200000) {
				lngMaxMilSec = 61200000;
			}
			rtn = lngMaxMilSec - lngMinMilSec;
		}
		return rtn;
	}

	// 获得时间段天数包括两端点
	public static int getDiffDaysWithWeekend(Date begin, Date end) {
		int rtn = getDiffDays(begin, end);
		return rtn = rtn + 1;
	}

	// 获得当前是星期几
	public static int getDayOfWeek(Date date) {
		return getCalendar(date).get(Calendar.DAY_OF_WEEK);
	}

	// 将Date转为Calendar
	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	// 获得一个月的第一天
	public static Date getFirstDayOfMonth(int year, int month) {
		Calendar cal = new GregorianCalendar(year, month, 1);
		return cal.getTime();

	}

	// 获得一个月的最后一天
	public static Date getLastDayOfMonth(int year, int month) {
		Calendar cal = new GregorianCalendar(year, month, 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	public static Date getFirstDayOfThisMonth() {
		Calendar cal1 = new GregorianCalendar();
		Calendar cal2 = new GregorianCalendar(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH), 1);
		return cal2.getTime();
	}

	public static Date getLastDayOfThisMonth() {
		Calendar cal1 = new GregorianCalendar();
		Calendar cal2 = new GregorianCalendar(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH), 1);
		cal2.set(Calendar.DAY_OF_MONTH, cal2.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal2.getTime();
	}

	public static Date getThisWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		return cal.getTime();
	}

	public static Date getNextWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, 7);
		return cal.getTime();
	}

	public static Date geLastWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, -7);
		return cal.getTime();
	}

	/**
	 * 获得当前日期与本周一相差的天数
	 * @return
	 */
	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			return -6;
		} else {
			return 2 - dayOfWeek;
		}
	}

	/**
	 * 获取一周 的  上一个周日
	 * @return
	 */
	public  static String getLastSunday() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus-1);
		Date monday = currentDate.getTime();
		return dateFormat.format(monday);
	}

	/**
	 * 获取一周 的  周一
	 * @return
	 */
	public  static String getCurrentMonday() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		return dateFormat.format(monday);
	}
	/**
	 * 获取一周的 周日
	 * @return
	 */
	public static String getCurrentSunday() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		Date sunday = currentDate.getTime();
		return dateFormat.format(sunday);
	}
}
