package com.hy.service;
/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/5/30
 * Time: 15:33
 */

import com.hy.dao.OnlyTest;
import com.hy.dao.OnlyTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther haoy
 * @create 2018/5/30
 */
@Service
public class OrderService {

    @Autowired
    private OnlyTestMapper onlyTestMapper;

    public void insertData(OnlyTest onlyTest) {
        onlyTestMapper.insert(onlyTest);
    }

    public OnlyTest getOrderInfo(String orderId) {
        OnlyTest onlyTest = new OnlyTest();
        onlyTest.setOrderid(orderId);
        return onlyTestMapper.selectOne(onlyTest);
    }
}
