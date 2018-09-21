package com.hy.restful.validator;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/9/18
 * Time: 15:35
 * 1、第一个参数是你要验证的注解，第二个参数是你要验证的类型是什么（如果写String，只有在类型是String的时候才会起作用，如果是Object就可以是任意类型）
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object>{

//    可以自动注入自己创建的service层，并且在类中不用标注@Service等注入标签，ConstraintValidator会帮我们自动注入
//    @Autowired
//    UserService userService;

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("init constraintAnnotation = " + constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("value = " + value);
        return false;
    }
}
