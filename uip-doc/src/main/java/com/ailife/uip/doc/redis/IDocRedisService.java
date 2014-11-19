package com.ailife.uip.doc.redis;

import com.ailife.uip.core.entity.Inter;
import com.ailife.uip.core.entity.Param;

/**
 * Created by chenmm6 on 2014/11/6.
 */
public interface IDocRedisService {

    public static final String ROOT_PARENT_SEQ = "-1";
    public static final String PUBLIC_REQ_PARENT_SEQ = "-2";
    public static final String PUBLIC_RESP_PARENT_SEQ = "-3";

    public void saveParam(Param param);

    public void saveInter(Inter inter);

}
