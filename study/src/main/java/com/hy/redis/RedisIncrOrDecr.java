package com.hy.redis;
/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/5/16
 * Time: 17:09
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @auther haoy
 * @create 2018/5/16
 */
@Service
public class RedisIncrOrDecr {

    @Autowired
    private RedisTemplate redisTemplate;

    public void redisTest(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {
                    Long increment = redisTemplate.opsForValue().increment("hytest", 1);
                    System.out.println(increment);
                    if(increment > 100){
                        redisTemplate.opsForValue().increment("hytest", -1);
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 500; i++) {
                    Long increment = redisTemplate.opsForValue().increment("hytest", 1);
                    System.out.println(increment);
                    if(increment > 100){
                        redisTemplate.opsForValue().increment("hytest", -1);
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    Long increment = redisTemplate.opsForValue().increment("hytest", 1);
                    System.out.println(increment);
                    if(increment > 100){
                        redisTemplate.opsForValue().increment("hytest", -1);
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {
                    Long increment = redisTemplate.opsForValue().increment("hytest", -1);
                    System.out.println(increment);
                    if(increment < 0){
                        redisTemplate.opsForValue().increment("hytest", 1);
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    Long increment = redisTemplate.opsForValue().increment("hytest", -1);
                    System.out.println(increment);
                    if(increment < 0){
                        redisTemplate.opsForValue().increment("hytest", 1);
                    }
                }
            }
        }).start();
    }

    public static void main(String[] args) {

    }
}
