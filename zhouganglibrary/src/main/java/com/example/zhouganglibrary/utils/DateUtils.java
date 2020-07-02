package com.example.zhouganglibrary.utils;

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 设定系统时间格式的工具类
 */

public class DateUtils {

    // yyyy-MM-dd hh:mm:ss 12小时制
    // yyyy-MM-dd HH:mm:ss 24小时制

    public static final String TYPE_01 = "yyyy-MM-dd HH:mm:ss";

    public static final String TYPE_02 = "yyyy-MM-dd";

    public static final String TYPE_03 = "HH:mm:ss";

    public static final String TYPE_04 = "yyyy年MM月dd日";

    public static final String TYPE_05 = "yyyy/MM/dd HH:mm:ss";

    public static final String TYPE_06 = "yyyy-MM-dd HH:mm";

    public static String formatDate(long time, String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        return new SimpleDateFormat(format).format(cal.getTime());
    }

    public static String formatDate(String longStr, String format) {
        try {
            return formatDate(Long.parseLong(longStr), format);
        } catch (Exception e) {
        }
        return "";
    }

    public static long formatStr(String timeStr, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(timeStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static String getCurrentTime() {
        return DateFormat.format("yyyy-MM-dd kk:mm:ss",
                new Date(System.currentTimeMillis())).toString();
//		return DateFormat.format("kk:mm",
//				new Date(System.currentTimeMillis())).toString();
    }

    public static String getCurrentTime(String format) {
        if (format == null) {
            format = "yyyy-MM-dd kk:mm:ss";
        }
        return DateFormat.format(format, new Date(System.currentTimeMillis()))
                .toString();
    }

    public static Calendar getCalendar(int y, int m, int d) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(y, m, d);
        return mCalendar;
    }

    public static Calendar getCalendar() {
        return Calendar.getInstance();
    }

    public static long getUnixTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    public static long getUnixTimeStamp(Date date) {
        return date.getTime() / 1000;
    }

    public static int getAge(long timeStamp) {
        Date beginDate = new Date(timeStamp * 1000);
        Date endDate = new Date(System.currentTimeMillis());
        int age = (int) ((endDate.getTime() - beginDate.getTime()) / 24 / 60
                / 60 / 1000 / 365);
        return age;
    }

    public static int getSecondToNow(long timeStamp) {
        Date beginDate = new Date(timeStamp * 1000);
        Date endDate = new Date(System.currentTimeMillis());
        return (int) ((endDate.getTime() - beginDate.getTime()) / 1000);
    }

    public static String getDuration(long mills) {
        long s = mills / 1000;
        if (s > 60) {
            int m = (int) (s / 60);
            if (m > 60) {
                int h = m / 60;
                return get0Num(h) + ":" + get0Num(m % 60) + ":" + get0Num((int) s % 60);
            } else {
                return "00:" + get0Num(m) + ":" + get0Num((int) s % 60);
            }
        } else {
            return "00:" + get0Num((int) s);
        }
    }

    //12:12:12
    public static String getTime(long time) {
        return String.format("%02d", time / 3600) + ":"
                + String.format("%02d", time % 3600 / 60) + ":"
                + String.format("%02d", time % 3600 % 60);
    }

    //12小时12分12秒
    public static String getTime2(long time) {
        String str = "";
        time = time / 1000;
        int s = (int) (time % 60);
        int m = (int) (time / 60 % 60);
        int h = (int) (time / 3600);
        str = h + "小时" + m + "分" + s + "秒";
        return str;
    }

    //12分12秒
    public static String getTime3(long time) {
        String str = "";

        time = time / 1000;
        int s = (int) (time % 60);
        if (s < 10) {

        }
        int m = (int) (time / 60 % 60);
        str = m + "分" + s + "秒";
        return str;
    }

    //12:12:12
    public static String getTime4(int time) {
        String str = "";
        String strS = "";
        String strM = "";
        String strH = "";
        int s = (int) (time % 60);
        int m = (int) (time / 60 % 60);
        int h = (int) (time / 3600);
        if (s < 10) {
            strS = "0" + s;
        } else {
            strS = s + "";
        }
        if (m < 10) {
            strM = "0" + m;
        } else {
            strM = m + "";
        }
        if (h < 10) {
            strH = "0" + h;
        } else {
            strH = h + "";
        }

        str = strH + ":" + strM + ":" + strS;
        return str;
    }

    //    整数(秒数)转换为时分秒格式(xx:xx:xx)
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    public static String get0Num(int n) {
        return n < 10 ? "0" + n : String.valueOf(n);
    }

    //ms转换成min
    public static String msToMin(long ms) {
        return (int) (ms / 1000 / 60) + "分钟";
    }

    public static Date getOneDayBefore(Date dateEnd){
        Calendar date = Calendar.getInstance();
        date.setTime(dateEnd);
        date.set(Calendar.DATE, date.get(Calendar.DATE) -2);
        return date.getTime();
    }

    //获取时间
    public static String getTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = format.format(date);

        return time;
    }
    public static String getTime2() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);

        return time;
    }
    public static String getTime3() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);

        return time;
    }
    public static String getStrTime(Long l) {
        Date date = new Date(l);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);

        return time;
    }
    public static String timestamp() {
        return DateFormat.format("yyyyMMddkkmmss",
                new Date(System.currentTimeMillis())).toString();
    }
    public static boolean isInEasternEightZones() {
        boolean defaultVaule = true;
        if (TimeZone.getDefault() == TimeZone.getTimeZone("GMT+08"))
            defaultVaule = true;
        else
            defaultVaule = false;
        return defaultVaule;
    }

    private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天
    private final static long month = 31 * day;// 月
    private final static long year = 12 * month;// 年

    /**
     * 返回文字描述的日期
     *
     * @param date
     * @return
     */
    public static String getTimeFormatText(Date date) {
        if (date == null) {
            return null;
        }
        long diff = new Date().getTime() - date.getTime();
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";

        }
        return "刚刚";
    }
    /**
     * 获取当前时区
     * @return
     */
    public  static String getCurrentTimeZone() {
        TimeZone tz = TimeZone.getDefault();
        String strTz = tz.getDisplayName(false, TimeZone.SHORT);
        return strTz;

    }
    public static String transform(String from){
        LogUtils.info("转换前="+from);
        String to = "";
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//本地时区
//        Calendar nowCal = Calendar.getInstance();
//        TimeZone localZone = nowCal.getTimeZone();

                String currentTimeZone = getCurrentTimeZone();
        LogUtils.info("时区="+currentTimeZone);
        TimeZone timeZone = TimeZone.getTimeZone(currentTimeZone);

//设定SDF的时区为本地
        simple.setTimeZone(timeZone);


//把字符串转化为Date对象，然后格式化输出这个Date
        Date fromDate = new Date();
        try {
            fromDate = simple.parse(from);
            to = simple.format(fromDate);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        LogUtils.info("转换后="+to);
        return to;
    }
    public static String friendly_time(Date time) {
        if (time == null) {
            return "";
        }
        String ftime = "";
        String currentTimeZone = getCurrentTimeZone();
        LogUtils.info("时区="+currentTimeZone);
        TimeZone timeZone = TimeZone.getTimeZone(currentTimeZone);

        Calendar cal = Calendar.getInstance(timeZone);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(timeZone);

        //判断是否是同一天
        String curDate = sdf.format(cal.getTime());
//        String curDate = dateFormater2.get().format(cal.getTime());
        String paramDate = sdf.format(time);
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分種前";
            else
                ftime = hour + "小時前";
            return ftime;
        }

        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days < 0) {
            ftime = sdf.format(time);
        } else if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0) {
                long max = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1);
                if (max < 10) {
                    ftime = "剛剛";
                } else {
                    ftime = max + "分鐘前";
                }
            } else
                ftime = hour + "小時前";
        } else if (days == 1) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour >24) {
                ftime = "昨天";
            } else
                ftime = hour + "小時前";


        } else if (days == 2) {
            ftime = "前天";
        } else {
            String curYear = curDate.substring(0, 4);
            String paramYear = paramDate.substring(0, 4);
            if (curYear.equals(paramYear)) {
                ftime = sdf.format(time);
                ftime = ftime.substring(5, 10);
                ftime = ftime.replaceFirst("-", "月");
                ftime = ftime + "日";
            } else {
                ftime = sdf.format(time);
                ftime = ftime.substring(0, 10);
                ftime = ftime.replaceFirst("-", "年");
                ftime = ftime.replace("-", "月");
                ftime = ftime + "日";
            }

        }
//        else if(days > 2 && days <= 10){
//            ftime = days+"天前";
//        }
//        else if(days > 10){
//            ftime = sdf.format(time);
//        }
        if (ftime.startsWith("-")) {
            ftime="剛剛";
        }
        return ftime;
    }


   public static long tolong(String st)
    {
        SimpleDateFormat  s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String currentTimeZone = getCurrentTimeZone();
//        LogUtils.info("时区="+currentTimeZone);
//        TimeZone timeZone = TimeZone.getTimeZone(currentTimeZone);
        s.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        try {
            return s.parse(st).getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
    }
}

