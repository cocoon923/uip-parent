package com.ailife.uip.redis.util;

import static com.ailife.uip.core.util.Symbol.*;

/**
 * Created by chenmm6 on 2014/11/3.
 */
public class RedisKeys {

	private static final String PLATFORM = "UIP";
	private static final String PROJECT = "DOC";

	private static final String CATEGORY = "CATEGORY";
	private static final String INTER = "INTER";
	private static final String PARAM = "PARAM";
	private static final String ID = "ID";

	private static String wrapAll(String source) {
		return (new StringBuilder()).append(PLATFORM).append(COLON).append(PROJECT).append(COLON).append(source).toString();
	}

	private static String join(String... sources) {
		StringBuilder sb = new StringBuilder();
		for (String source : sources) {
			sb.append(source).append(COLON);
		}
		int lastIndexOf = sb.lastIndexOf(COLON);
		if (lastIndexOf != -1 && lastIndexOf == sb.length() - 1) {
			return sb.substring(0, sb.length() - 1);
		} else {
			return sb.toString();
		}
	}

}
