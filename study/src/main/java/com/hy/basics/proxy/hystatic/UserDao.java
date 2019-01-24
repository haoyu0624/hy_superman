package com.hy.basics.proxy.hystatic;

/**
 * 接口实现
 * 目标对象
 */
public class UserDao implements IUserDao {

    @Override
    public void save() {
        System.out.println("----已经保存数据!----");
        update();
    }

    public void update() {
        System.out.println("----已经更新数据!----");
    }
}