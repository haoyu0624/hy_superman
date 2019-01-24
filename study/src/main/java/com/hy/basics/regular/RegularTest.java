package com.hy.basics.regular;

/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/5/18
 * Time: 9:48
 */
public class RegularTest {

    public static void main(String[] args) {
//        elasticsearch.octo_trigger_index_request=logstash-dev-trigger-analy-request-*
//        elasticsearch.octo_warn_index=logstash-dev-warning-analy-20*
//        String s = "logstash-dev-trigger-analy-request-*";
        String s = "logstash-dev-trigger-analy-request-20*";
        if(s.endsWith("-*")){
            String s1 = s.substring(0, s.lastIndexOf("-"));
            s = s1 + "2018.05.18";
        }
        System.out.println(s);

        String replaceParam = "logstash-dev-trigger-analy-request-20*";
        int i = replaceParam.lastIndexOf("-");
        String substring = replaceParam.substring(0,i);
        System.out.println("=======>>"+substring+"-"+"212131");
//        String s1 = replaceParam.replaceAll("-*$", "12132");
//        System.out.println("==========>>"+s1);
//        Pattern p = Pattern.compile("-");
//        Matcher m = p.matcher(replaceParam);
//        if(m.find(m.end())){
//            String matchParam = m.group();
////            String matchValue = matchParam.replace("2018.05.18");
//        }


    }
}
