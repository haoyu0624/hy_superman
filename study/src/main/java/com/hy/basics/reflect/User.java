package com.hy.basics.reflect;
/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/6/5
 * Time: 10:45
 */

import com.alibaba.fastjson.JSONObject;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @auther haoy
 * @create 2018/6/5
 */
public class User {

    public void add(int a, int b){
        System.out.println(a+b);
    }

    public static void main(String[] args) {
//        int a = 3;
//        int b = a;
//        a = 4;
//        System.out.println("b = " + b);
//
//        List c = new ArrayList();
//        c.add(1);
//        c.add(2);
//        List d = c;
//        c.add(3);
//        System.out.println("d.size() = " + d.size());
//        String ss = "1";
//        String[] split = ss.split(",");
//        String[] value = {"1","2"};
//        plus(split);
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println("zoneId = " + zoneId);
        LocalDate now = LocalDate.now();
        LocalDate localDate = now.plusDays(7);
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(zoneId);
        Date from = Date.from(zonedDateTime.toInstant());
        System.out.println("from = " + from);
//        localDate.
//                Date.from()
    }

    public static void plus(String ... value){
        List<String> strings = Arrays.asList(value);
        System.out.println("strings = " + JSONObject.toJSONString(strings));
    }
}
