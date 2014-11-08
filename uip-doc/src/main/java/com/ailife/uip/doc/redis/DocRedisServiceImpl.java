package com.ailife.uip.doc.redis;

import com.ailife.uip.core.entity.Inter;
import com.ailife.uip.core.entity.ItemRelat;
import com.ailife.uip.core.entity.JsonBean;
import com.ailife.uip.core.entity.Param;
import com.ailife.uip.core.util.LogUtil;
import com.ailife.uip.core.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ailife.uip.core.util.RedisKey.*;

/**
 * Created by chenmm6 on 2014/11/6.
 */
@Service
public class DocRedisServiceImpl implements IDocRedisService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public void saveRootParam(Param root) {
		if (!isRootParam(root)) {
			LogUtil.error(this.getClass(), "Param is not root param");
			return;
		}
		String rootParamKey = getRootParamKey(root);
		if (StringUtils.isNullorEmpty(rootParamKey)) {
			LogUtil.error(this.getClass(), "Param info error! -> ", root.toString());
		}

		String rootParamValue = stringRedisTemplate.opsForValue().getAndSet(rootParamKey, root.toJSON());
		if (StringUtils.isNullorEmpty(rootParamValue)) {
			LogUtil.debug(this.getClass(), "Save root Param! -> " + root.toString());
		} else {
			if (!Param.toBean(rootParamValue).equals(root)) {
				LogUtil.debug(this.getClass(), "Update root Param! -> " + root.toString());
			}
		}
	}

	private boolean isRootParam(Param root) {
		return (root != null && "-1".equals(root.getParentSeq()));
	}

	@Override
	public void saveParam(Param param) {
		try {
			//1.Save Param detail infomation.
			stringRedisTemplate.opsForValue().set(getParamKey(param.getSeq()), param.toJSON());
			//2.Add Param to param pool.
			stringRedisTemplate.opsForSet().add(PARAM_POOL_KEY, param.getSeq());
			//3.Mapping paramCode/paramType to paramSeq;
			stringRedisTemplate.opsForValue().set(getParamMappingKey(param), param.getSeq());
		} catch (Exception e) {
			LogUtil.error(this.getClass(), e, "Save Param Error!");
		}
	}

	@Override
	public void saveInter(Inter inter) {
		//1. Save inter and add to inter pool.
		stringRedisTemplate.opsForValue().set(getInterKey(inter.getSeq()), inter.toJSON());
		stringRedisTemplate.opsForSet().add(INTER_POOL_KEY, inter.getSeq());

		//2. Save params and add to param pool.
		Map<String, String> paramMap = new HashMap<String, String>();
		List<String> paramSeqList = new ArrayList<String>();
		for (Param param : inter.getParams()) {
			paramMap.put(getParamKey(param.getSeq()), param.toJSON());
			paramSeqList.add(param.getSeq());
		}
		stringRedisTemplate.opsForValue().multiSet(paramMap);
		stringRedisTemplate.opsForSet().add(PARAM_POOL_KEY, paramSeqList.toArray(new String[paramSeqList.size()]));

		//3. Save item relat between inter and param and add to item relat pool.
		Map<String, String> itemRelatMap = new HashMap<String, String>();
		List<String> itemRelatSeqList = new ArrayList<String>();
		for (ItemRelat itemRelat : inter.getItemRelats()) {
			itemRelatMap.put(getItemRelatKey(itemRelat.getSeq()), itemRelat.toJSON());
			itemRelatSeqList.add(itemRelat.getSeq());
		}
		stringRedisTemplate.opsForValue().multiSet(itemRelatMap);
		stringRedisTemplate.opsForSet().add(ITEM_RELAT_POOL_KEY, itemRelatSeqList.toArray(new String[itemRelatSeqList
				.size()]));
	}

	@Override
	public void batchSaveParam(List<Param> params) {
		Map<String, String> paramMap = new HashMap<String, String>();
		List<String> paramSeqList = new ArrayList<String>();
		for (Param param : params) {
			paramMap.put(getParamKey(param.getSeq()), param.toJSON());
			paramSeqList.add(param.getSeq());
		}
		stringRedisTemplate.opsForValue().multiSet(paramMap);
		stringRedisTemplate.opsForSet().add(PARAM_POOL_KEY, paramSeqList.toArray(new String[paramSeqList.size()]));
	}
}
