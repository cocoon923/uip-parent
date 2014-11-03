package com.ailife.uip.redis.config;

import com.ailife.uip.core.util.StringUtils;
import com.ailife.uip.redis.core.JedisConfig;
import com.ailife.uip.redis.core.JedisConnectionConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by chenmm on 2014/11/3.
 */
@Configuration
public class RedisAutoConfiguration {

    public static final String CONFIGURATION_PREFIX = "uip.redis";

    @Configuration
    @ConditionalOnMissingBean(JedisConfig.class)
    protected static class JedisConfigConfiguration {

        public JedisConfig jedisConfig() {
            return new JedisConfig();
        }

    }

    @Configuration
    @ConditionalOnMissingBean(JedisConnectionConfig.class)
    protected static class JedisConnectionConfigConfiguration {

        @Autowired
        private RedisProperties redisProperties;

        @Bean
        public JedisConnectionConfig jedisConnectionConfig() {
            String host = redisProperties.getHost();
            int port = redisProperties.getPort();
            String password = redisProperties.getPassword();
            int timeout = redisProperties.getTimeout();
            if (StringUtils.isNotNullorEmpty(password)) {
                if (timeout == 0) {
                    return new JedisConnectionConfig(host, port, password);
                } else {
                    return new JedisConnectionConfig(host, port, password, timeout);
                }
            } else {
                if (timeout == 0) {
                    return new JedisConnectionConfig(host, port);
                } else {
                    return new JedisConnectionConfig(host, port, timeout);
                }
            }
        }

    }


    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(300);
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setMaxWaitMillis(1000);
        jedisPoolConfig.setTestOnBorrow(true);
        return jedisPoolConfig;
    }


    @Configuration
    @ConditionalOnMissingBean(JedisConnectionFactory.class)
    @ConditionalOnBean({JedisConnectionConfig.class, JedisPoolConfig.class})
    protected static class JedisConnectionFactoryConfiguration {

        @Autowired
        private JedisPoolConfig jedisPoolConfig;

        @Autowired
        private JedisConnectionConfig jedisConnectionConfig;

        @Bean
        public JedisConnectionFactory jedisConnectionFactory() {
            JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
            jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
            jedisConnectionFactory.setUsePool(true);
            jedisConnectionFactory.setHostName(jedisConnectionConfig.getHostname());
            jedisConnectionFactory.setPort(jedisConnectionConfig.getPort());
            jedisConnectionFactory.setTimeout(jedisConnectionConfig.getTimeout());
            if (jedisConnectionConfig.hasPassword()) {
                jedisConnectionFactory.setPassword(jedisConnectionConfig.getPassword());
            }
            return jedisConnectionFactory;
        }

    }


}
