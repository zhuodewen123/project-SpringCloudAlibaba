package com.zdw.common.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
import java.time.Duration;


/**
 * redis配置
 * @author 卓德文
 */
@Slf4j
@EnableCaching
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * redis前缀(命名规则-- 前缀:缓存名:)
     */
    @Value("${spring.redis.prefix}")
    private String redisPrefix;

    /**
     * 序列化配置(RedisTemplate)
     */
    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new     GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(factory);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 设置 redis 数据默认过期时间，默认6小时
     * 设置@cacheable 序列化方式
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration = configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                //不缓存null值
                .disableCachingNullValues()
                //缓存加上前缀，避免不同项目部署在同一个redis导致缓存互相覆盖,
                //改为用一个冒号(spring默认的生成key策略用的两个冒号)
                //.computePrefixWith(cacheName -> StrUtil.isEmpty(this.redisPrefix) ? cacheName + ":" : this.redisPrefix +":"+cacheName+":")
                // 设置过期时间，这里设置的不会影响其他地方用redisTemplate
                .entryTtl(Duration.ofHours(6));
        return configuration;
    }

    /**
     * 异常处理(当Redis发生异常时，打印日志，但是程序正常走)
     * @return
     */
    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        log.info("初始化 -> [{}]", "Redis CacheErrorHandler");
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                log.error("Redis occur handleCacheGetError：key -> [{}]", key, e);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
                log.error("Redis occur handleCachePutError：key -> [{}]；value -> [{}]", key, value, e);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
                log.error("Redis occur handleCacheEvictError：key -> [{}]", key, e);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                log.error("Redis occur handleCacheClearError：", e);
            }
        };
    }
}
