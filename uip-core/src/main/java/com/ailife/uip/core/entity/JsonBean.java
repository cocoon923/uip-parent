package com.ailife.uip.core.entity;

import com.ailife.uip.core.util.ReflectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenmm6 on 2014/11/6.
 */
public abstract class JsonBean<T> implements Json {

    protected abstract T getT();

    @Override
    public String toJSON() {
        Field[] fields = getT().getClass().getDeclaredFields();
        List<String> list = new ArrayList<String>();
        for (Field field : fields) {
            if (ReflectionUtil.isBeanField(field) && ReflectionUtil.isPrimitiveOrString(field)) {
                list.add(field.getName());
            }
        }
        return JSONObject.toJSONString(getT(), new SimplePropertyPreFilter(list.toArray(new String[list.size()])));
    }

    protected static <T extends Json> T toBean(String json, Class<T> clz) {
        return JSON.parseObject(json, clz);
    }

}
