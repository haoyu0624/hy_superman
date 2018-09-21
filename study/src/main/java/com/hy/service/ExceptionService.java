package com.hy.service;
/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/5/30
 * Time: 15:33
 */

import com.hy.exception.AppWebException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther haoy
 * @create 2018/5/30
 */
@Service
public class ExceptionService {

//    @Async
    public Map getData() throws AppWebException {
        try{
            Map map = new HashMap();
            map.put("a","123");
//            testAsync();
            System.out.println("getData1");
            return map;
        }catch (Exception e){
            throw new AppWebException(500,"dfadfdsfsdfsdfsd");
        }
    }

    private void testAsync() {
        try {
            Thread.sleep(5000);
            System.out.println("testAsync2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
