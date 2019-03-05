package com.hy;

import com.hy.redis.RedisIncrOrDecr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/5/17
 * Time: 13:56
 */

//@EnableAutoConfiguration
@SpringBootApplication(exclude={ HibernateJpaAutoConfiguration.class})
@RequestMapping("/haoy")
@EnableAsync
//@MapperScan("com.hy.dao")
public class HaoyApplication {

    @Autowired
    private RedisIncrOrDecr redisIncrOrDecr;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/redis")
    @ResponseBody
    String redisIncr() {
        redisIncrOrDecr.redisTest();
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HaoyApplication.class, args);
    }
}
