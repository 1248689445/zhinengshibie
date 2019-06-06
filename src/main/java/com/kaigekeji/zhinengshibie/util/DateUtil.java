package com.kaigekeji.zhinengshibie.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 描述：日期工具类 <br>
 */
public class DateUtil {
	
	public static final String DATE_PATTERN = "yyyy-MM-dd";

	public static final String YYYY = "yyyy";
	
	public static final String TIME_PATTERN = "hh:mm:ss";
	
	public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";


	public static String getyyyy() {
		Date date = new Date(); // 新建此时的的系统时间
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
//		String str = format.format(date); // 转为字符串
		return format.format(date);
	}


	private DateUtil() throws Exception {
		throw new Exception("不允许实例化");
	}

	/**
	 * 获取当前时间，日期格式如：2017-07-28 09:57:43
	 * 
	 * @return {@link Date()} 返回当前时间
	 */
	public static String getNow() {
		Date date = new Date(); // 新建此时的的系统时间
		SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_PATTERN);
//		String str = format.format(date); // 转为字符串
		return format.format(date);
	}
	
	/**
	 * 获取当前时间，日期格式如：2017-07-28
	 * 
	 * @return {@link Date()} 返回当前时间
	 */
	public static String getNowS() {
		Date date = new Date(); // 新建此时的的系统时间
		SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
		String str = format.format(date); // 转为字符串
		return str;
	}

	/**
	 * 根据指定格式获取当前时间
	 * 
	 * @return {@link Date()} 返回当前时间0
	 */
	public static String getNow(String pattern) {
		Date date = new Date(); // 新建此时的的系统时间
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		String str = format.format(date); // 转为字符串
		return str;
	}

	
	/**
	 * String类型转Date类型
	 * 
	 * @param str 日期格式的字符串形式，如：2017-07-28 09:57:43
	 * @return {@link Date()} Date类型的返回值
	 */
	public static Date str2Date(String str) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return format.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Date类型转String类型
	 * 
	 * @param date 传入一个Date类型的参数
	 * @return {@link String} 日期格式的字符串形式，如：2017-07-28 09:57:43
	 */
	public static String date2Str(Date date) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return format.format(date);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Date类型转String类型
	 * 
	 * @param date 传入一个Date类型的参数
	 * @return {@link String} 日期格式的字符串形式，如：2017-07-28 09:57:43
	 */
	public static String date2Str(Date date, String pattern) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			return format.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取下一小时的时间，日期格式如：2017-07-28 09:57:43
	 * 
	 * @return 获取下一小时的时间，日期格式如：2017-07-28 09:57:43
	 */
	public static String getNextHour() {
		return date2Str(new Date(str2Date(getNow()).getTime() + 1 * 1000 * 60 * 60));
	}

	/**
	 * 获取下一小时的时间，日期格式如：2017-07-28 09:57:43
	 * 
	 * @return 获取下一小时的时间，日期格式如：2017-07-28 09:57:43
	 */
	public static String getNextHour(String now) {
		return date2Str(new Date(str2Date(now).getTime() + 1 * 1000 * 60 * 60));
	}

	/**
	 * 把一个日期转换为指定的格式，formater为空则格式为：yyyy-MM-dd
	 * 
	 * @param strDate {@link String} 源字符日期
	 * @param formater {@link String} 日期格式，如：yyyy-MM-dd、yyyy年MM月dd日等
	 * @return
	 */
	public static Date str2Date(String strDate, String formater) {
		if (strDate == null) {
			return null;
		}
		if (formater == null) {
			formater = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat df = new SimpleDateFormat(formater);
		Date date = new Date();
		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			pe.getStackTrace();
		}
		return date;
	}

	/**
	 * 获取第二天时间，日期格式如：2017-07-28 09:57:43
	 * 
	 * @return {@link Date()} 返回当前时间0
	 */
	public static String getNextDay() {
		Date date = new Date();// 新建此时的的系统时间
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, +1);// +1今天的时间加一天
		date = calendar.getTime();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = format.format(date); // 转为字符串
		System.out.println("第2天时间:" + str);
		return str;
	}

	/**
	 * 返回两时间的相差天数
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int getDayNumBetween2Date(Date d1, Date d2) {

		if (isEqualsInYMD(d1, d2)) {
			return 0;
		}

		long l = d2.getTime() - d1.getTime();
		int day = (int) (l / (24 * 60 * 60 * 1000));
		if (day < 0) {
			day = (-day);
		}

		int m = (int) (l % (24 * 60 * 60 * 1000));
		Calendar c = Calendar.getInstance();
		if (d1.compareTo(d2) <= 0) {
			c.setTime(d1);
			c.add(Calendar.MILLISECOND, m);
			if (isEqualsInYMD(d1, c.getTime())) {
				return day;
			} else {
				return day + 1;
			}
		} else {
			c.setTime(d2);
			c.add(Calendar.MILLISECOND, m);
			if (isEqualsInYMD(d2, c.getTime())) {
				return day;
			} else {
				return day + 1;
			}
		}

	}

	/**
	 * 两个时间是否是 同年 同月 同日 如果是，则返回true，否则返回false
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean isEqualsInYMD(Date d1, Date d2) {

		Calendar c = Calendar.getInstance();

		c.setTime(d1);
		int year1 = c.get(Calendar.YEAR);
		int dayOfYear1 = c.get(Calendar.DAY_OF_YEAR);

		c.setTime(d2);
		int year2 = c.get(Calendar.YEAR);
		int dayOfYear2 = c.get(Calendar.DAY_OF_YEAR);

		if (year1 != year2) {
			return false;
		}
		if (dayOfYear1 != dayOfYear2) {
			return false;
		}

		return true;
	}
	
	/**
	 * 计算两个时间之间相差的时间数，例：0天0时0分钟
	 * @param beginDate
	 * @param endDate
	 * @return 
	 */
	public static String getDatePoor(Date beginDate, Date endDate) {
		 
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    // long ns = 1000;
	    // 获得两个时间的毫秒时间差异
	    long diff = beginDate.getTime() - endDate.getTime();
	    if(diff < 0) {
	    	diff = -diff;
	    }
	    // 计算差多少天
	    long day = diff / nd;
	    // 计算差多少小时
	    long hour = diff % nd / nh;
	    // 计算差多少分钟
	    long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    // long sec = diff % nd % nh % nm / ns;
	    return day + "天" + hour + "小时" + min + "分钟";
	}
	
	/**
	 * 计算两个日期之间相差的月份
	 * 
	 * @param date1
	 * @param date2
	 * @param pattern
	 * @return
	 */
	public static int getMonth(String date1, String date2, String pattern) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);  
	        String str1 = date1;  
	        String str2 = date2;  
	        Calendar bef = Calendar.getInstance();  
	        Calendar aft = Calendar.getInstance();  
	        bef.setTime(sdf.parse(str1));  
	        aft.setTime(sdf.parse(str2));  
	        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);  
	        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;  
	        return Math.abs(month + result);     
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * 计算两个日期之间相差的时间
	 * 
	 * @param day1 {@link Date} Date类型的日期对象
	 * @param day2 {@link Date} Date类型的日期对象
	 * @return {@link String} 精确到秒的时间差
	 */
	public static String calculateTimes(Date day1, Date day2) {
		long time1 = day1.getTime();
		long time2 = day2.getTime();
		long diff = Math.abs(time1 - time2);
		int dayNs = 1000 * 60 * 60 * 24;
		int hourNs = 1000 * 60 * 60;
		int minuteNs = 1000 * 60;
		int secondNs = 1000;
		long day = diff / dayNs;
		long hour = (diff % dayNs) / hourNs;
		long minute = (diff % dayNs % hourNs) / minuteNs;
		long second = (diff % dayNs % hourNs % minuteNs) / secondNs;
		StringBuffer sb = new StringBuffer();
		sb.append(day).append("天");
		sb.append(hour).append("小时");
		sb.append(minute).append("分钟");
		sb.append(second).append("秒");
		return sb.toString();
	}

	/**
	 * 新增int类型时间 
	 */
	public static String getInteger() {
		long timeStamp = System.currentTimeMillis();  //获取当前时间戳,也可以是你自已给的一个随机的或是别人给你的时间戳(一定是long型的数据)
		String s3 = Long.toString(timeStamp).substring(0,10);  
		return s3;
	}
	
	/**
	 * int转date类型
	 */
	public static String getD(int date) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_PATTERN);
//		 int seconds=1531126880;//这是你数据库提出的数据
	      long millions=new Long(date).longValue()*1000;
//	      System.out.println("QQQQ："+format.format(millions));
		return format.format(millions);
	}
	
	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		Date date = new Date(); // 新建此时的的系统时间
		SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_PATTERN);
		System.out.println("当前时间："+format.format(date));
		System.out.println("int转data："+format.format(1537931318000L));
		
 
        int seconds=1531126880;//这是你数据库提出的数据
        long millions=new Long(seconds).longValue()*1000;
        System.out.println("int转data："+millions);
        System.out.println("int转data："+ format.format(millions));
		
		
//		long timeStamp = 1531126880000L;//直接是时间戳
		long timeStamp = System.currentTimeMillis();  //获取当前时间戳,也可以是你自已给的一个随机的或是别人给你的时间戳(一定是long型的数据)
		 String s3 = Long.toString(timeStamp);  
		System.out.println(s3.substring(0,10));
		SimpleDateFormat sdf=new SimpleDateFormat(DATE_TIME_PATTERN);//这个是你要转成后的时间的格式
		String sd = sdf.format(new Date(timeStamp));   // 时间戳转换成时间
	    System.out.println(sd);//打印出你要的时间
		

//		String bday="2018/02/10";
//		System.out.println(bday.replace("/", "-"));     //生日
		
		
//		
//		String data="2018-06-29 11:25:44";
//		System.out.println(data.substring(0,10));
//		try{
//			InetAddress myip= InetAddress.getLocalHost();
//			System.out.println("你的IP地址是："+myip.getHostAddress());
//			System.out.println("主机名为："+myip.getHostName());
//			}catch(Exception e){
//			e.printStackTrace();
//			}
//			}
		getyyyy();
	    getInteger();
	    getD(1531126880);
//		getNow(); // 当前时间
//		getNextDay(); // 第2天时间
//		System.out.println(calculateTimes(DateUtil.str2Date("2018-12-31 00:00:00", "yyyy-MM-dd HH:mm:ss"), DateUtil.str2Date("2018-12-31 00:59:59", "yyyy-MM-dd HH:mm:ss")));
}
}
