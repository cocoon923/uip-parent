package com.ailife.uip.redis.core;


import com.ailife.uip.core.entity.JSONBean;

/**
 * Contains parameters of redis configuration.
 *
 * @author chenmm
 */
public final class JedisConfig extends JSONBean {

	public String put(Field field, String value) {
		return super.put(field.toString(), value);
	}

	public String get(Field field) {
		return this.get(field.toString());
	}

	@Override
	public String put(String key, String value) {
		Field field = Field.valueOf(key);
		if (field != null) {
			return this.put(field, value);
		}
		return null;
	}

	/**
	 * Define parameters' enumeration of redis configuration.
	 *
	 * @author chenmm
	 */
	enum Field {
		maxIdle, minIdle, maxActive, maxWait, whenExhaustedAction, testOnBorrow, testOnReturn, testWhileIdle, timeBetweenEvictionRunsMillis, numTestsPerEvictionRun, minEvictableIdleTimeMillis, softMinEvictableIdleTimeMillis
	}

}
