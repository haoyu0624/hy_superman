package com.hy.controller;
/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/5/17
 * Time: 14:16
 */

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hy.exception.AppWebException;
import com.hy.result.ResultDO;
import com.hy.service.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther haoy
 * @create 2018/5/17
 */
@Controller
@RequestMapping("/haoy")
public class ControllerTest {

    @Autowired
    ExceptionService exceptionService;

    @RequestMapping("/hello")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/exceptionTest")
    @ResponseBody
    ResultDO exceptionTest(HttpServletRequest request) throws AppWebException, JsonProcessingException {
        StringBuffer requestURL = request.getRequestURL();
        System.out.println("requestURL = " + requestURL);
        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);
        String queryString = request.getQueryString();
        System.out.println("queryString = " + queryString);
        if(!StringUtils.isEmpty(queryString)){
            requestURL = new StringBuffer(requestURL + "?" + queryString);
        }
        System.out.println("queryStringAll = " + requestURL);
        ResultDO resultDO = new ResultDO();
        Map map = exceptionService.getData();
        map.put("123",null);
        ObjectMapper ob = new ObjectMapper();
        ob.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String string = ob.writeValueAsString(map);
        HashMap hashMap = JSONObject.parseObject(string, HashMap.class);
        System.out.println("完事儿了");
        resultDO.setData(hashMap);
        return resultDO;
    }

    @RequestMapping("/getCurrUser")
    @ResponseBody
    String getCurrUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("loginid","admin");
        request.getRequestDispatcher("/admin/getCurrUser").forward(request,response);
        return "Hello World!";
    }

    @RequestMapping("/homePage")
    String homePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 创建cookie对象
        Cookie cookie = new Cookie("name", "HY");
        //设置其生命周期
        cookie.setMaxAge(3600*24*3);
        response.addCookie(cookie);
        return "home";
    }

}
