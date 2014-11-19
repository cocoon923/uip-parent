package com.ailife.uip.doc.util;

import com.ailife.uip.core.util.RedisKey;
import com.ailife.uip.core.util.Symbol;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

/**
 * Created by chenmm6 on 2014/11/3.
 */
@Service
public class ID implements ApplicationContextAware{

    private static final Long MIN = 1000000L;
    private static final Long DELTA = 1L;

    private static ApplicationContext atx;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        atx = applicationContext;
    }

    public static String getNewId() {
        ID instance = atx.getBean(ID.class);
        if (instance.stringRedisTemplate != null) {
            BoundValueOperations<String, String> ops = instance.stringRedisTemplate.boundValueOps(RedisKey.ID_GENERATOR_KEY);
            ops.setIfAbsent(String.valueOf(MIN));
            return String.valueOf(ops.increment(DELTA));
        } else {
            return instance.idGenerator.generateId().toString().replaceAll(Symbol.MINUS, Symbol.EMPTY);
        }
    }

}
