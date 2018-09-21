package com.hy.restful;

import com.fasterxml.jackson.annotation.JsonView;
import com.hy.restful.bean.User;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/9/18
 * Time: 10:49
 */
@RestController
@RequestMapping("/userManage")
public class UserController {

//    @RequestMapping(value = "/user/{id:\\d+}",method = RequestMethod.GET) 简写后可用下面这种方式
    @GetMapping("/user/{id:\\d+}")
    public User getUser(@PathVariable String id){
        User user = new User();
        user.setName("小花");
        user.setAge(18);
        user.setPassword("123456");
        return user;
    }

    @RequestMapping(value = "/userSimple/{id}",method = RequestMethod.GET)
    @JsonView(User.UserSimple.class)
    public User getUserSimple(@RequestBody String id){
        User user = new User();
        user.setName("小花");
        user.setAge(18);
        user.setPassword("123456");
        return user;
    }

    @RequestMapping(value = "/userDetail/{id}",method = RequestMethod.GET)
    @JsonView(User.UserDetail.class)
    public User getUserDetail(@PathVariable String id){
        User user = new User();
        user.setName("小花");
        user.setAge(18);
        user.setPassword("123456");
        return user;
    }

    @PostMapping("/user")
    public User create(@Valid @RequestBody User user, BindingResult erros){
        if(erros.hasErrors()){
            erros.getAllErrors().stream().forEach(e -> System.out.println(e.getDefaultMessage()));
        }
        System.out.println("user = " + user.toString());
        System.out.println("user = " + ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));
        return user;
    }

    @PutMapping("/user")
    public User update(@Valid @RequestBody User user, BindingResult erros){
        if(erros.hasErrors()){
            erros.getAllErrors().stream().forEach(e -> System.out.println(e.getDefaultMessage()));
        }
        System.out.println("user = " + user.toString());
        System.out.println("user = " + ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));
        return user;
    }

    /**
     * 测试异常处理
     * @param user
     * @return
     */
    @PutMapping("/userTest")
    public User updateTest(
//            @Valid
            @RequestBody User user){
        throw new RuntimeException("出错了");
//        System.out.println("user = " + user.toString());
//        System.out.println("user = " + ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));
//        return user;
    }

}
