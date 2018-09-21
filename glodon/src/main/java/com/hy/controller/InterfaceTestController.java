package com.hy.controller;
/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/5/9
 * Time: 11:04
 */

import com.hy.entity.EventStatusInfoRecord;
import com.hy.mongodb.MongoEventInfoRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @auther haoy
 * @create 2018/5/9
 */
@Controller
//@SpringBootApplication
@RequestMapping("/interfaceTest")
public class InterfaceTestController {

    long start;

    TreeSet ss = new TreeSet();

    @RequestMapping("/test1")
    @ResponseBody
        //@RequestHeader String adminId,
    String homeTest(@RequestParam String salary, @RequestParam String name, @RequestHeader(required = false) String adminId, HttpServletRequest request, HttpServletResponse response) {
        if("2".equals(adminId)){
            start = System.currentTimeMillis();
        }
//        String ss1 = null;
//        ss1.indexOf(1);
        String remoteHost = request.getRemoteHost();
        int remotePort = request.getRemotePort();
        int serverPort = request.getServerPort();
        String headerLength = request.getHeader("Content-Length");
        String protocol = request.getProtocol();
        String connection = request.getHeader("connection");
        String transferEncoding = request.getHeader("Transfer-Encoding");
        Enumeration<String> headerNames = request.getHeaderNames();
        String requestURI = request.getRequestURI();
        StringBuffer requestURLBuffer = request.getRequestURL();
        Map<String, String[]> parameterMap = request.getParameterMap();
        String queryString = request.getQueryString();
        String servletPath = request.getServletPath();

        try {
            ServletInputStream inputStream = request.getInputStream();
            BufferedReader in=new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String xmlHead = "";
            String xmlContent="";
            String line = null;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
//            System.out.println("=============sb===========>>>"+sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(headerNames.hasMoreElements()){
//            System.out.println("=============headerNames.nextElement()============"+headerNames.nextElement());
        }

        ss.add(remotePort);
//        System.out.println(ss);
//        System.out.println("======================>>"+remoteHost+":"+remotePort+":"+serverPort);
        System.out.println("======adminId======"+adminId);
//        System.out.println("======headerLength======"+headerLength);
//        System.out.println("======protocol======"+protocol);
//        System.out.println("======connection======"+connection);
//        System.out.println("======transferEncoding======"+transferEncoding);
//        System.out.println("======requestURI======"+requestURI);
//        System.out.println("======requestURLBuffer======"+requestURLBuffer);

        response.addHeader("connection","open");
//        //retryTest.testRetry();
//        System.out.println("=================NB=================");
        if("245".equals(adminId)){
            long end = System.currentTimeMillis();
            System.out.println(end-start);
        }
        return "{\"status\":\"success\"}";
    }


    public static void main(String[] args) {
//        Map sourceResClone = new HashMap(null);

        Map result = new HashMap();
        BigDecimal successReqCountDecimal = new BigDecimal(10);
        BigDecimal failReqCountDecimal = new BigDecimal(1);
        BigDecimal totalCountDecimal = successReqCountDecimal.add(failReqCountDecimal);
        if(totalCountDecimal.compareTo(BigDecimal.ZERO) == 0){
            result.put(8,"100");
        }
        BigDecimal divide = successReqCountDecimal.divide(totalCountDecimal, 4, RoundingMode.DOWN);
        System.out.println(divide.toString());
        BigDecimal multiply = successReqCountDecimal.divide(totalCountDecimal, 4, RoundingMode.DOWN).multiply(new BigDecimal(100));
        System.out.println(multiply.toString());
//        double percent = successReqCountDecimal.divide(totalCountDecimal, 4, RoundingMode.DOWN).multiply(new BigDecimal(100)).doubleValue();
//        System.out.println(percent);
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String format = decimalFormat.format(multiply);
        System.out.println(format);
    }
}
