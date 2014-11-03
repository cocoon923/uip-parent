package com.ailife.uip.core.entity;

import com.ailife.uip.core.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

public class JSONBean extends HashMap<String, String> implements Cloneable {

	private static final long serialVersionUID = 1154936899953328326L;

	public JSONBean() {
		super();
	}

	public JSONBean(String str) {
		super();
		initialFromJSONStr(str);
	}

	public JSONBean(int initialCapacity, String str) {
		super(initialCapacity);
		initialFromJSONStr(str);
	}

	public JSONBean(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	public JSONBean(int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * Returns a {@link JSONObject} representation of this {@link JSONBean}.
	 *
	 * @return {@link JSONObject}
	 */
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		for (String key : this.keySet()) {
			obj.put(key, this.get(key));
		}
		return obj;
	}

	@Override
	public String toString() {
		return this.toJSON().toString();
	}

	private void initialFromJSONStr(String str) {
		if (StringUtils.isNotNullorEmpty(str)) {
			JSONObject obj = JSON.parseObject(str);
			for (String key : obj.keySet()) {
				this.put(key, obj.getString(key));
			}
		}
	}

	@Override
	public String get(Object key) {
		String value = super.get(key);
		if (!StringUtils.isNotNullorEmpty(value)) {
			value = "";
		}
		return value;
	}

	@Override
	public Object clone() {
		JSONBean target = (JSONBean) super.clone();
		for (String key : this.keySet()) {
			target.put(key, this.get(key));
		}
		return target;
	}

}
