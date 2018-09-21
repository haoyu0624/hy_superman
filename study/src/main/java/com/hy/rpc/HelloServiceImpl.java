package com.hy.rpc;
/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/7/24
 * Time: 16:16
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "Hello " + name;
    }
}
