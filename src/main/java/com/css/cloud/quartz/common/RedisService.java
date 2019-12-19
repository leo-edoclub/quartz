package com.css.cloud.quartz.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * Created by wang.wei on2018/9/12
 */
@Component
public class RedisService {
    @Autowired
    public   RedisTemplate redisTemplate;

    /**
     * 保存数据到redis
     * @param key
     * @param value
     */
    public  void set(String key, Object value) {
        ValueOperations<String, Object> ops =redisTemplate.opsForValue();
        ops.set(key, value);//暂时不设置过期时间
//        ops.set(key, value,120, TimeUnit.SECONDS);
    }

    public  List get(String key){
        ValueOperations<String, Object> ops =redisTemplate.opsForValue();
        return (List)ops.get(key);
    }
    public Set getRegexp(String reg){
      return redisTemplate.keys("");
    }
}
