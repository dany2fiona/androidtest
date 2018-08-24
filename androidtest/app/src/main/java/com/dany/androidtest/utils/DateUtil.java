package com.dany.androidtest.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by dan.y on 2018/8/6.
 */

public class DateUtil {
    public static String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    public static long dateToStamp(String time) throws ParseException {
        SimpleDateFormat sdr = new SimpleDateFormat(FORMAT_YMDHMS, Locale.CHINA);
        Date date = sdr.parse(time);
        return date.getTime();
    }

    public static String stampToDate(long lt){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_YMDHMS,Locale.CHINA);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }


}
