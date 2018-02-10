package com.lc.redis.config;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableCaching
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400 * 30)
public class RedisConfig extends CachingConfigurerSupport{
	
	@Value("${spring.redis.host}")
    private String host;
	@Value("${spring.redis.port}")
	private int port;
	@Value("${spring.redis.pool.max-wait}")
	private int timeout;

	@Bean
    public KeyGenerator keyGenerator() {
    	return new KeyGenerator() {
			
    		public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append("_").append(method.getName());
                for (Object obj : params) {
                    sb.append("_").append(obj.toString());
                }
                return sb.toString();
            }
		};
    }

    @SuppressWarnings("rawtypes")
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        //设置缓存过期时间
        //rcm.setDefaultExpiration(60);//秒
        return rcm;
    }
    
    
    @Bean(name="redisTemplate")
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        @SuppressWarnings({ "rawtypes", "unchecked" })
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        JedisConnectionFactory jc = (JedisConnectionFactory) factory;
        System.out.println(jc.getHostName());
        return template;
    }
    
    @Bean  
    public JedisConnectionFactory redisConnectionFactory() {  
            JedisConnectionFactory factory = new JedisConnectionFactory();  
            factory.setHostName(host);  
            factory.setPort(port); 
            factory.setPassword(null);
            factory.setTimeout(timeout); //设置连接超时时间  
            return factory;  
    }

}