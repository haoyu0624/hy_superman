package com.hy.util;
/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/5/17
 * Time: 14:39
 */

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther haoy
 * @create 2018/5/17
 */
public class DateUtil {

    public static void main(String[] args) throws ParseException {

        String ss8 = null;
        Integer ddd = null;
        boolean empty = StringUtils.isEmpty(ddd);
        System.out.println(empty);
        Integer integer = Integer.valueOf(ss8);
        System.out.println(integer);


        //日期转毫秒数
        String ss = "2018-06-01";
        String ss1 = "2018-06-02";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sdf.parse(ss);
        Date parse1 = sdf.parse(ss1);
        int i = parse.compareTo(parse1);
        System.out.println(i);
        System.out.println("日期转毫秒数"+ss+"毫秒数为："+parse.getTime());
        //毫秒转日期
        long time = 1529923728670L;
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
