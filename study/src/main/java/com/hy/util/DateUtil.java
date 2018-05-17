package com.hy.util;
/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/5/17
 * Time: 14:39
 */

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther haoy
 * @create 2018/5/17
 */
public class DateUtil {

    public static void main(String[] args) {
        long time = 1526461650834L;
        millisecondConvertDate(time);
    }

    /**
     * 毫秒转日期
     * @param mill
     */
    private static void millisecondConvertDate(long mill){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制
        Date date2 = new Date();
        date2.setTime(mill);
        System.out.println("毫秒转换时间"+simpleDateFormat.format(date2));
    }
}
