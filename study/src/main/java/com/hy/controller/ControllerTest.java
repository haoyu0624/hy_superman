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
import com.hy.dao.OnlyTest;
import com.hy.exception.AppWebException;
import com.hy.result.ResultDO;
import com.hy.service.ExceptionService;
import com.hy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    OrderService orderService;

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

    /**
     * 幂等性测试
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/idempotencyTest")
    @ResponseBody
    String idempotencyTest(@RequestBody HashMap map){
        String orderId = map.get("orderId").toString();
        System.out.println("orderId = " + orderId);
        //1.查询redis是否存在（锁机制，加个过期时间）
        Long add = stringRedisTemplate.opsForSet().add("testOrder", orderId);
//        Jedis jedis = this.jedisPool.getResource();
//        Jedis jedis = new Jedis("localhost");
//        Long sadd = jedis.sadd(orderId, "1");
        if(add == 0){
            return "重复数据";
        }
        //2.查询此订单是否已完成，完成直接返回
        OnlyTest onlyTest = orderService.getOrderInfo(orderId);
        if(onlyTest != null){
            return onlyTest.getOrderid();
        }
        //3.执行入库流程（保存失败需删除redis）
        OnlyTest onlyTestNew = new OnlyTest();
        try{
            onlyTestNew.setOrderid(orderId);
            orderService.insertData(onlyTestNew);
            //4.存入订单的数据
        }catch (Exception e){
            System.out.println("e = " + e);
            return "fail";
        }finally {
            stringRedisTemplate.opsForSet().remove("testOrder", orderId);
        }
        return onlyTestNew.getOrderid();


        //1.查询订单是否已经支付,如果已经支付，直接返回已支付

        //2.未支付，查询redis中是否存在该订单号的key,已存在返回重复操作

        //3.不存在，向redis中增加该订单号的key

        //4.再次查询是否支付，如果没有则进行支付，支付完成后删除该订单号的Key


    }


}
