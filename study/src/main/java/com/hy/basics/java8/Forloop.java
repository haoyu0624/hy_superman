package com.hy.basics.java8;
/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/6/12
 * Time: 17:16
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @auther haoy
 * @create 2018/6/12
 */
public class Forloop {
    public static void main(String[] args) throws ParseException {
        Integer in = 1;
        Integer in2 = 1;
        if(in == in2){
            System.out.println("1");
        }

        if(in.equals(in2)){
            System.out.println("2");
        }

        List<Map> list = new ArrayList();
        Map m1 = new HashMap();
        m1.put("name",1);
        list.add(m1);
        Map m2 = new HashMap();
        m2.put("name",2);
        list.add(m2);
        Map m3 = new HashMap();
        m3.put("name",3);
        list.add(m3);
        List list2 = new ArrayList();
        list.stream().collect(Collectors.toList());

//        list.forEach(item->{
//            list2.add(item.);
//        });
//        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse("2018-08-25");

        Date end = sdf.parse("2018-08-31");

        LocalDateTime localDateTime = LocalDateTime.ofInstant(start.toInstant(), ZoneId.systemDefault());
        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(end.toInstant(), ZoneId.systemDefault());
        Duration between = Duration.between(localDateTime, localDateTime1);
        long l = between.toDays();
        System.out.println("l = " + l);
//
//        list.forEach(item-> list2.add(item.get("name")));
//        System.out.println(list2.toString());
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date parse = sdf.parse(sdf.format(new Date()));
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(parse);
//        calendar.add(Calendar.DAY_OF_MONTH, -1);//日期回滚1天
//        System.out.println("1===>>"+calendar.getTime());
//        long end = calendar.getTimeInMillis();
//
//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.setTime(parse);
//        calendar1.add(Calendar.DAY_OF_MONTH, -7);//日期回滚1天
//        long start = calendar1.getTimeInMillis();
//        System.out.println("2===>>"+calendar1.getTime());
//
//        long diffDays = (end - start) / (1000 * 60 * 60 * 24)+2;
//        System.out.println("3===>>"+diffDays);
//
//        Calendar calendar2 = Calendar.getInstance();
//        calendar2.setTime(parse);
//        calendar2.add(Calendar.DAY_OF_MONTH, -1);//日期回滚1天
//        System.out.println("4===>>"+calendar2.getTime());
//
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.setTime(parse);
//        calendar3.add(Calendar.DAY_OF_MONTH, -(int)diffDays);//日期回滚1天
//        System.out.println("5===>>"+calendar3.getTime());

    }
}
