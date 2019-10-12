package cn.ytmj.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author rui
 * @create 2019-10-11 23:24
 */
public class DateUtils {
    //日期转换成字符串
    public static String dateToString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String format = sdf.format(date);
        return format;
    }
    //字符串转换成日期

    public static Date stringToDate(String str, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date parse = sdf.parse(str);
        return parse;
    }
}
