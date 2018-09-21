package com.hy.redis;
/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/5/16
 * Time: 17:09
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
@Service
public class RedisIncrOrDecr {

    @Autowired
    private RedisTemplate redisTemplate;

    public void redisTest(){
//        Boolean hytest = redisTemplate.expire("hytest", 1, TimeUnit.MINUTES);
//        if(hytest){
//            Long increment = redisTemplate.opsForValue().increment("hytest", 1);
//        }else{
//            redisTemplate.expire("hytest", 1, TimeUnit.MINUTES);
//        }

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 100; i++) {
//                    incr("hytest");
//                }
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 100; i++) {
//                    decr("hytest");
//                }
//            }
//        }).start();

        checkMaxNumber(999L);

    }


    public boolean incr(String key) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.incrementAndGet();
        System.out.println(Thread.currentThread()+":incr+:"+increment);
        if (null == increment || increment.longValue() == 0) {//初始设置过期时间
            entityIdCounter.expire(10, TimeUnit.HOURS);
        }
        if(increment != null && increment > 2){
            entityIdCounter.decrementAndGet();
            System.out.println(Thread.currentThread()+":incr-:"+increment);
            return false;
        }
        return true;
    }

    public Long decr(String key) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.decrementAndGet();
        System.out.println(Thread.currentThread()+":decr+:"+increment);
//        if(increment < 0){
//            entityIdCounter.incrementAndGet();
//            System.out.println(Thread.currentThread()+":decr-:"+increment);
//        }

//        long increment = entityIdCounter.get();
//        if(increment > 0){
//            entityIdCounter.decrementAndGet();
//        }
        return increment;
    }

    public boolean checkMaxNumber(Long redisKey) {
        String key = redisKey.toString();
        RedisAtomicLong entityIdCounter = new RedisAtomicLong("counter"+key, redisTemplate.getConnectionFactory());
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try{
            long value = entityIdCounter.get();
            if(value > 10){
                return true;
            }
            return false;
        }catch (DataRetrievalFailureException e){
            System.out.println("redis清除");
            return false;
        }
    }

    public static void main(String[] args) {

    }
}
