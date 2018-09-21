package com.hy.action;
/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/5/28
 * Time: 15:32
 */

import com.hy.entity.EventMessage;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @auther haoy
 * @create 2018/5/28
 */
public class ActionTest {

    public static void main(String[] args) {
        ActionTest actionTest = new ActionTest();
        String replaceParam = "adminId=${id}&name=${name}&desc=${name}";
        Map custData = new HashMap();
        custData.put("id","4455");
        custData.put("name","zhang$44");
        Set unmatchSet = new HashSet();
        String s = actionTest.replaceData(false, replaceParam, custData, unmatchSet);
        System.out.println("s============>"+s);

        EventMessage e = new EventMessage("11",2L,true);
        Long eventId = e.getEventId();
        e.setEventId(3L);
        System.out.println("========>"+eventId);
    }
    private String replaceData(boolean transcoding, String replaceParam, Map custData, Set unmatchSet) {
        if (!StringUtils.hasText(replaceParam)) {
            return "";
        }
        Pattern p = Pattern.compile("\\$\\{.*?\\}");
        Matcher m = p.matcher(replaceParam);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String matchParam = m.group();
            String matchValue = matchParam.substring(2, matchParam.length() - 1);
            Object custDataValue = custData.get(matchValue);
            if (null == custDataValue) {
                unmatchSet.add(matchValue);//增加未匹配的数据以便发送告警
                m.appendReplacement(sb, "");
                continue;
            }
            if (transcoding) {
                try {
                    custDataValue = URLEncoder.encode(custDataValue.toString(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("不支持的编码方式: " + e.getMessage(), e);
                }
            }
            //			if(custDataValue instanceof String){
            //				custDataValue = ("'"+custDataValue+"'");
            //			}
            String replace = custDataValue.toString().replace("\"", "\\\\\"");
            replace = replace.replaceAll("\\$", "\\\\\\$");
            m.appendReplacement(sb, replace);
        }
        return m.appendTail(sb).toString();
    }

}
