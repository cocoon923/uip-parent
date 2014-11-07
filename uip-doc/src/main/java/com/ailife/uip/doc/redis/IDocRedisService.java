package com.ailife.uip.doc.redis;

import com.ailife.uip.core.entity.Inter;
import com.ailife.uip.core.entity.Param;

import java.util.List;

/**
 * Created by chenmm6 on 2014/11/6.
 */
public interface IDocRedisService {

	public void saveParam(Param param);

	public void saveInter(Inter inter);

	public void batchSaveParam(List<Param> params);

}
