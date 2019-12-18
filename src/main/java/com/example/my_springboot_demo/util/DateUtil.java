package com.example.my_springboot_demo.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期处理工具类
 */
public class DateUtil
{
	/**默认显示日期的格式:yyyy-MM-dd*/
	public static final String DATAFORMAT_STR = "yyyy-MM-dd";
	/**显示日期的格式:yyyy-MM*/
	public static final String DATAFORMAT_STRM = "yyyy-MM";
	public static final String DAYTIMEFORMAT_STR = "ddHHmmss";
	public static final String DATETIMEFORMAT_STR = "yyyyMMddHHmmss";
	public static final String DATETIMEFORMAT_STR2="yyyy-MM-dd HH:mm:ss";
	public static final String DATETIMEFORMAT_STR3="yyyy-MM-dd HH:mm";
	public static final String DATETIMEFORMAT_STR4="yyyy年MM月dd日HH时mm分";
	public static final String YYYYMMDDHH = "yyyyMMddHH";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYMMDDHHmmSSsss = "yyMMddHHmmssSSS";
	public static final String YYYYMMDDHHmmSSsss = "yyyyMMddHHmmssSSS";
	public static final String MMDDHHmm = "M月d日 HH:mm";
	public static final String YYMMDD = "yy年MM月dd日";

	public static final String todaySdf = "HH:mm";
	public static final String yesterdaySdf = "HH:mm 昨天 ";
	public static final String otherSdf = "HH:mm MM-dd";

	/**
	 * 字符串转为日期
	 * @param str
	 * @param format
	 * @return
	 */
	public static Date str2Date(String str, String format) {
		if (null == str || "".equals(str)) {
			return null;
		}
		Date date = null;
		try {
			date = new SimpleDateFormat(format).parse(str);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式
	 * @return 字符串
	 */
	public static String date2Str(Date date, String format) {
		if (null == date) {
			return null;
		}
		return new SimpleDateFormat(format).format(date);
	}

	// 将传入时间与当前时间进行对比，是否今天昨天  
	public static String changeTime(Date date) {
		SimpleDateFormat sfd = null;  
		String time = "";  
		Calendar dateCalendar = Calendar.getInstance();  
		dateCalendar.setTime(date);  
		Date now = new Date();  
		Calendar targetCalendar = Calendar.getInstance();  
		targetCalendar.setTime(now);  
		targetCalendar.set(Calendar.HOUR_OF_DAY, 0);  
		targetCalendar.set(Calendar.MINUTE, 0);  
		if (dateCalendar.after(targetCalendar)) {  
			sfd = new SimpleDateFormat(todaySdf);  
			time = sfd.format(date);  
			return time;  
		} else {  
			targetCalendar.add(Calendar.DATE, -1);  
			if (dateCalendar.after(targetCalendar)) {  
				sfd = new SimpleDateFormat(yesterdaySdf);  
				time = sfd.format(date);  
				return time;  
			}  
		}  
		sfd = new SimpleDateFormat(otherSdf);  
		time = sfd.format(date);  
		return time;  
	}  

	/**
	 * 计算两个时间之间的天数
	 * @param c1 减数
	 * @param c2 被减数
	 * @return
	 */
	public static int DateDiff(Calendar c1,Calendar c2)
	{
		return DateDiff(c1,c2,"D");
	}

	/**
	 * 计算两个时间之间的差值，根据标志的不同而不同
	 * @param c1
	 * @param c2
	 * @param dateUtil 标志，表示按照月M/日D/时H/分m/秒s等计算
	 * @return
	 */
	public static int DateDiff(Calendar c1,Calendar c2,String dateUtil)
	{
		return DateDiff(c1.getTime(),c2.getTime(),dateUtil);
	}

	/**
	 * 计算两个时间之间的天数
	 * @param d1 减数
	 * @param d2 被减数
	 * @return
	 */
	public static int DateDiff(Date d1,Date d2){
		return DateDiff(d1,d2,"D");
	}

	/**
	 * 计算两个时间之间的差值，根据标志的不同而不同
	 * @param d1
	 * @param d2
	 * @param dateUnit 标志，表示按照年(Y)/月(M)/日(D)/时(H)/分(m)/秒(s)等计算
	 * @return
	 */
	public static int DateDiff(Date d1,Date d2,String dateUnit)
	{		
		int int_return;
		try {
			long l = d1.getTime() - d2.getTime();
			int_return = 0;
			if(dateUnit.equals("s"))
				int_return = (int)(l / (1000));
			if(dateUnit.equals("m"))
				int_return = (int)(l / (1000 * 60));
			if(dateUnit.equals("H"))
				int_return = (int)(l / (1000 * 60 * 60));
			if(dateUnit.equals("D"))
				int_return = (int)(l / (1000 * 60 * 60 * 24));
			if(dateUnit.equals("M"))
			{			
//				int_return = (d1.getYear()-d2.getYear())*12 + 
//						(d1.getMonth()-d2.getMonth())-1 + 
//						((d1.getMonth()-d2.getMonth())==0?(d1.getDate()>=d2.getDate()?1:0):(d1.getDate()>=d2.getDate()?1:0));
				Calendar dc1 =Calendar.getInstance();  
				dc1.setTime(d1);  
				Calendar dc2 =Calendar.getInstance();  
				dc2.setTime(d2); 
				int_return = (dc1.get(Calendar.YEAR) - dc2  
						.get(Calendar.YEAR))* 12 + dc1.get(Calendar.MONTH)  
						- dc2.get(Calendar.MONTH);  
			}
			if(dateUnit.equals("Y"))
			{			
				Calendar dc1 =Calendar.getInstance();  
				dc1.setTime(d1);  
				Calendar dc2 =Calendar.getInstance();  
				dc2.setTime(d2); 
				int_return = dc1.get(Calendar.YEAR) - dc2.get(Calendar.YEAR);
			}
		} catch (RuntimeException e) {

			return 0;
		}
		return int_return;
	}
	
	/**
	 * 
	 * 查询时用的开始时间
	 * @param date
	 * @return
	 * @exception
	 */
	public static Timestamp getStartTime(Date date) {
		if (date != null) {
			Calendar dateCalendar = Calendar.getInstance();
			dateCalendar.setTime(date);
			Calendar calendar = new GregorianCalendar(dateCalendar.get(Calendar.YEAR),
					dateCalendar.get(Calendar.MONTH), dateCalendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			return new Timestamp(calendar.getTimeInMillis());
		} else {
			return null;
		}
	}
	/**
	 * 
	 * Description: 查询时用的结束时间
	 * @param date
	 * @return
	 * @exception
	 */
	public static Timestamp getEndTime(Date date) {
		if (date != null) {
			Calendar dateCalendar = Calendar.getInstance();
			dateCalendar.setTime(date);
			Calendar calendar = new GregorianCalendar(dateCalendar.get(Calendar.YEAR),
					dateCalendar.get(Calendar.MONTH), dateCalendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
			return new Timestamp(calendar.getTimeInMillis());
		} else {
			return null;
		}
	}

	/** 
	 * 得到几天前的时间 
	 * @param d 
	 * @param day 
	 * @return 
	 */  
	public static Date getDateBefore(Date d,int day){  
		Calendar now =Calendar.getInstance();  
		now.setTime(d);  
		now.set(Calendar.DATE,now.get(Calendar.DATE)-day);  
		return now.getTime();  
	}  

	/** 
	 * 得到几天后的时间 
	 * @param d 
	 * @param day 
	 * @return 
	 */  
	public static Date getDateAfter(Date d,int day){  
		Calendar now =Calendar.getInstance();  
		now.setTime(d);  
		now.set(Calendar.DATE,now.get(Calendar.DATE)+day);  
		return now.getTime();  
	}  

	/**
	 * 获取N月后第一天,N支持正负
	 * @return
	 */
	public static Date getMonthFirstDate(Date d,int month){
		Calendar curCal = Calendar.getInstance();
		curCal.setTime(d);
		curCal.add(Calendar.MONTH, month);
		curCal.set(Calendar.DAY_OF_MONTH, 1);
		Date beginTime = curCal.getTime();
		return beginTime;
	}
	/**
	 * 获取N月后最后一天,N支持正负
	 * @return
	 */
	public static Date getMonthLastDate(Date d,int month){
		Calendar curCal = Calendar.getInstance();
		curCal.setTime(d);
		curCal.add(Calendar.MONTH, month);
		curCal.set(Calendar.DATE, 1);
		curCal.roll(Calendar.DATE, -1);
		Date endTime = curCal.getTime();
		return endTime;
	}

	/**
	 * 获取本月第一天
	 * @return
	 */
	public static Date getMonthFirstDate(Date d){
		Calendar curCal = Calendar.getInstance();
		curCal.setTime(d);
		curCal.set(Calendar.DAY_OF_MONTH, 1);
		Date beginTime = curCal.getTime();
		return beginTime;
	}
	/**
	 * 获取本月最后一天
	 * @return
	 */
	public static Date getMonthLastDate(Date d){
		Calendar curCal = Calendar.getInstance();
		curCal.setTime(d);
		curCal.set(Calendar.DATE, 1);
		curCal.roll(Calendar.DATE, -1);
		Date endTime = curCal.getTime();
		return endTime;
	}


	/** 
	 * 得到N月后的时间 ,N支持正负
	 * @return 
	 */  
	public static Date getMonth(Date d,int month){  
		Calendar now =Calendar.getInstance();  
		now.setTime(d);  
		now.add(Calendar.MONTH, month);  
		return now.getTime();  
	}
	
	/**
	 * 得到a和b时间差相同的上一个周期
	 * @param a
	 * @param b
	 * @return
	 */
	public static Date getLastCycleDate(Date a,Date b){
		Long timea=a.getTime();
		Long timeb=b.getTime();
		Calendar c = Calendar.getInstance();  
		c.setTimeInMillis(Math.min(timea, timeb)-Math.abs(timea-timeb));
		return c.getTime();
	}
	
	/**
	 * 
	 * @param compareObject 被判断的时间 
	 * @param a 时间范围a
	 * @param b 时间范围b
	 * @return when compareObject between a and b return true otherwise return false
	 */
	public static Boolean isBetweenTime(Date compareObject,Date a,Date b){
		if(compareObject.before(a)==true&&compareObject.before(b)==false){
			return true;
		}else if(compareObject.before(b)==true&&compareObject.before(a)==false){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 *
	 * @param timeMillis
	 * @return
	 */
	public static String formatDateTime(long timeMillis) {
		long day  = timeMillis / (24 * 60 * 60 * 1000);
		long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
		long min  = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s    = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long sss  = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
		return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
	}

	/**
	 * 得到开始时间
	 * @param date
	 * @return
	 */
	public static Date getDateStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 今天的开始时间
	 * @return
	 */
	public static Date getTodayStart() {
		return getDateStart(new Date());
	}

	/**
	 * 昨天的开始时间
	 * @return
	 */
	public static Date getYesterdayStart() {
		Date     todayStart = getTodayStart();
		Calendar calendar   = Calendar.getInstance();
		calendar.setTime(todayStart);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
		return calendar.getTime();
	}

	/**
	 * 明天的开始时间
	 * @return
	 */
	public static Date getTomorrowStart() {
		Date     todayStart = getTodayStart();
		Calendar calendar   = Calendar.getInstance();
		calendar.setTime(todayStart);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
		return calendar.getTime();
	}

	public static Date getMonthStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取当前时间 yyyyMMddHHmmss
	 *
	 * @return String
	 */
	public static String getCurrentTime() {
		Date             now       = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String           s         = outFormat.format(now);
		return s;
	}

	/**
	 * 在只定日期基础上，获取指定天数后的时间
	 * @return
	 */
	public static Date addDay(Date now,int day){
		long nowM = now.getTime() ;
		long t = nowM + day*24*60*60*1000;
		return new Date(t);
	}

	public static void main(String[] args) throws ParseException {
		System.out.println(new String("CHN-UNICOM"));
		System.out.println(new String("\\u4E2D\\u56FD\\u79FB\\u52A8"));

	}
}

