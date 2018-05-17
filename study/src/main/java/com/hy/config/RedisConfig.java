package com.hy.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * @author liuk-m
 *
 */
@Configuration
@EnableRedisRepositories(basePackages= "com.hy.redis")
@ComponentScan(basePackages={"com.hy.redis"})
@AutoConfigureAfter(AppConfig.class)
public class RedisConfig {

}
