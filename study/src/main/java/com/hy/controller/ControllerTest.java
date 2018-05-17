package com.hy.controller;
/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/5/17
 * Time: 14:16
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther haoy
 * @create 2018/5/17
 */
@Controller
@RequestMapping("/haoy")
public class ControllerTest {

    @RequestMapping("/hello")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

}
