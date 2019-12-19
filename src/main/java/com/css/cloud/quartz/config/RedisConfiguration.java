package com.css.cloud.quartz.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by wang.wei on2018/8/13
 */
@Configuration
@Slf4j
public class RedisConfiguration {
//    @Bean
//    @ConfigurationProperties(prefix = "spring.redis.pool")
//    public JedisPoolConfig jedisPoolConfig(){
//        JedisPoolConfig config = new JedisPoolConfig();
//        //最大空闲数
////        config.setMaxIdle(10);
//        //最小空闲数
////        config.setMinIdle(5);
//        //最大链接数
////        config.setMaxTotal(20);
//        return config;
//    }

//    @Bean
//    @ConfigurationProperties(prefix = "spring.redis")
//    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig config){
//        log.info("maxIdle的默认值"+config.getMaxIdle());
//        log.info("minIdle的默认值"+config.getMinIdle());
//        log.info("maxTotal的默认值"+config.getMaxTotal());
//
//        JedisConnectionFactory factory = new JedisConnectionFactory();
//        factory.setPoolConfig(config);
////        factory.setHostName("127.0.0.1");
//        //端口
////        factory.setPort(6379);
//        return factory;
//    }

//


//    @Bean
//    public RedisTemplate<String,Object> redisTemplate(JedisConnectionFactory factory){
//        JedisPoolConfig config = new JedisPoolConfig();
//        log.info("maxIdle的默认值"+config.getMaxIdle());
//        log.info("minIdle的默认值"+config.getMinIdle());
//        log.info("maxTotal的默认值"+config.getMaxTotal());
//        log.info(factory.getHostName());
//        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(factory);
//        //为key设置序列化器
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        //为value设置序列化器
//        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
//        return redisTemplate;
//    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//
//        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置value的序列化规则和 key的序列化规则
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}