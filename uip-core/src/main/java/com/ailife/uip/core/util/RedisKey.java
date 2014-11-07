package com.ailife.uip.core.util;

import com.ailife.uip.core.entity.Param;

import static com.ailife.uip.core.util.Symbol.*;

/**
 * Created by chenmm6 on 2014/11/6.
 */
public class RedisKey {

	public static String getInterKey(String interSeq) {
		return wrapAll(join(INTER, interSeq));
	}

	public static String getParamKey(String paramSeq) {
		return wrapAll(join(PARAM, paramSeq));
	}

	public static String getItemRelatKey(String itemRelatSeq) {
		return wrapAll(join(ITEM_RELAT, itemRelatSeq));
	}

	public static String getParamMappingKey(Param param) {
		return wrapAll(join(PARAM, MAPPING, param.getParamCode(), param.getParamType()));
	}

	private static final String PLATFORM = "UIP";
	private static final String PROJECT = "DOC";

	private static final String POOL = "POOL";
	private static final String ROOT = "ROOT";

	private static final String INTER = "INTER";
	private static final String PARAM = "PARAM";
	private static final String ITEM_RELAT = "ITEM:RELAT";

	private static final String REQUEST = "0";
	private static final String RESPONSE = "1";
	private static final String MAPPING = "MAPPING";

	public static final String INTER_POOL_KEY = wrapAll(join(INTER, POOL));
	public static final String PARAM_POOL_KEY = wrapAll(join(PARAM, POOL));
	public static final String ITEM_RELAT_POOL_KEY = wrapAll(join(ITEM_RELAT, POOL));

	public static final String REQUEST_ROOT_PARAM_KEY = wrapAll(join(PARAM, ROOT, REQUEST));
	public static final String RESPONSE_ROOT_PARAM_KEY = wrapAll(join(PARAM, ROOT, RESPONSE));

	private static String wrapAll(String key) {
		return (new StringBuilder(PLATFORM)).append(COLON).append(PROJECT).append(COLON).append(key).toString();
	}

	private static String join(String... keys) {
		StringBuilder sb = new StringBuilder();
		for (String key : keys) {
			sb.append(key).append(COLON);
		}
		if (sb.length() > 0 && (sb.lastIndexOf(COLON) == sb.length() - 1)) {
			return sb.substring(0, sb.length() - 1);
		} else {
			return sb.toString();
		}
	}

}
