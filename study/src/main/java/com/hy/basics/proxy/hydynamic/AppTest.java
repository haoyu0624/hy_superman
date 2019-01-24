package com.hy.basics.proxy.hydynamic;

import com.hy.basics.proxy.hystatic.IUserDao;
import com.hy.basics.proxy.hystatic.UserDao;

public class AppTest {
    public static void main(String[] args) throws Exception {
        // 目标对象
        UserDao target = new UserDao();
        // 【原始的类型 class cn.itcast.b_dynamic.UserDao】
        System.out.println(target.getClass());

        // 给目标对象，创建代理对象
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        // class $Proxy0   内存中动态生成的代理对象
        System.out.println(proxy.getClass());

        // 执行方法   【代理对象】
        proxy.save();
    }
}
