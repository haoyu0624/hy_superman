package com.hy.restful.bean;

import com.fasterxml.jackson.annotation.JsonView;
import com.hy.restful.validator.MyConstraint;

import javax.validation.constraints.NotBlank;

/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/9/18
 * Time: 10:52
 */
public class User {

    public interface UserSimple{};
    public interface UserDetail extends UserSimple{};

    @MyConstraint(message = "这是一个自定义注解的测试")
    private String name;
    @NotBlank
    private String password;
    private Integer age;

    @JsonView(value=UserSimple.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonView(value=UserDetail.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @JsonView(value=UserSimple.class)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
