package com.cb.system.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat(
			"yyyy-MM-dd");
	
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat(
	"yyyyMMdd");

	private final static SimpleDateFormat sdfMinute = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");
	
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	
	
	/**
	 * 从字符串中分析日期
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
    public static Date parseDate(String dateStr) throws ParseException {
        Date date=null;
        String[] dateArray = dateStr.split("\\D+");     //+防止多个非数字字符在一起时导致解析错误
        int dateLen = dateArray.length;
        int dateStrLen=dateStr.length();
        if(dateLen>0){
            if(dateLen==1&&dateStrLen>4){
                if(dateStrLen=="yyyyMMddHHmmss".length()){
                    //如果字符串长度为14位并且不包含其他非数字字符，则按照（yyyyMMddHHmmss）格式解析
                    date=parseDate(dateStr,"yyyyMMddHHmmss");
                }else if(dateStrLen=="yyyyMMddHHmm".length()){
                    date=parseDate(dateStr,"yyyyMMddHHmm");
                }else if(dateStrLen=="yyyyMMddHH".length()){
                    date=parseDate(dateStr,"yyyyMMddHH");
                }else if(dateStrLen=="yyyyMMdd".length()){
                    date=parseDate(dateStr,"yyyyMMdd");
                }else if(dateStrLen=="yyyyMM".length()){
                    date=parseDate(dateStr,"yyyyMM");
                }
            }else{
                String fDateStr=dateArray[0];
                for(int i=1;i<dateLen;i++){
                    //左补齐是防止十位数省略的情况
                    fDateStr+=leftPad(dateArray[i],"0",2);
                }
                 
                if(dateStr.trim().matches("^\\d{1,2}:\\d{1,2}(:\\d{1,2})?$")){
                    //补充年月日3个字段
                    dateLen+=3;
                    fDateStr=formatDate(new Date(),"yyyyMMdd")+fDateStr;
                }
                 
                date=parseDate(fDateStr,"yyyyMMddHHmmss".substring(0, (dateLen-1)*2+4));
            }
        }
         
        return date;
    }
    
    /**
     * 按照给定的格式化字符串解析日期
     * 使用方法：  Date date=DateUtil.parseDate("20130803", "yyyyMMdd");
     * @param dateStr
     * @param formatStr
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String dateStr, String formatStr) throws ParseException {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        date = sdf.parse(dateStr);
        return date;
    }
    
  //左补齐
    public static String leftPad(String str,String pad,int len){
        String newStr=(str==null?"":str);
        while(newStr.length()<len){
            newStr=pad+newStr;
        }
        if(newStr.length()>len){
            newStr=newStr.substring(newStr.length()-len);
        }
        return newStr;
    }
    
  //按照给定的格式化字符串格式化日期
    public static String formatDate(Date date, String formatStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
    }
	
	/**
	 * 获取今天日期
	 * @return
	 */
	public static Date getToday(){
		return new Date();
	}
	
	/**
	 * 获取前后几日的日期
	 * @param date
	 * @param i
	 * @return
	 */
	public static Date getDayAfterBeforeToday(Date date,int i){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, i);
		return cal.getTime();
	}
	
	/**
	 * 获取本周一
	 * @return
	 */
	public static Date getTimesWeekMonday() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}
	
	/**
	 * 获得本周日
	 * @return
	 */
	public static Date getTimesWeekSunday() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getTimesWeekMonday());
		cal.add(Calendar.DAY_OF_WEEK, 6);
		return cal.getTime();
	}
	
	/**
	 * 使日期格式化
	 * @param date
	 * @return
	 */
	public static String getDayFormat(Date date)
	{
		if(date==null){
			return null;
		}
		return sdfDay.format(date);
	}
	
	/**
	 * 获取yyyy-MM-dd hh:mm 格式的日期
	 * @return
	 */
	public static String getSdfMinute(){
		return sdfMinute.format(new Date());
	}
	
	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}
	
	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	* @Title: compareDate
	* @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	* @param s
	* @param e
	* @return boolean  
	* @throws
	* @author luguosui
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}
	  /**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long 
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
      
        return day;
    }
    
    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    /**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        
        return dateStr;
    }
    

}
