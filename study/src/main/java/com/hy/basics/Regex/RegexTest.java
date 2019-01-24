package com.hy.basics.Regex;
/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/6/7
 * Time: 19:10
 */


/**
 * @auther haoy
 * @create 2018/6/7
 */
public class RegexTest {
    public static void main(String[] args) {
        //String ss = "{\\"ID\\":\\"1\\","Code":"22","QuoteTime":"2013-12-12","Last":"22"}"
        String strJson = "{\"ID\":\"1\"}";
//        strJson = Regex.Replace(strJson, "(?<=\"ID\":\")[^\",]*", "55");
        String result = "all|proxy";
        String pattern1 = "[0,1,2]";
        String pattern2 = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]) "
                + "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
        String pattern3 = "all|proxy|trigger";
        boolean matches = result.matches(pattern3);
        System.out.println("matches = " + matches);


        String pattern4 = "[a-zA-Z_0-9]{4,16}";
        String name = null;
        boolean matches1 = name.matches(pattern4);
        System.out.println("matches1 = " + matches1);
    }
}
