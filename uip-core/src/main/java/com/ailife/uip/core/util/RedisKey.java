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
		return wrapAll(join(PARAM, MAPPING, param.getParamCode(), param.getParamType(), param.getParentSeq()));
	}

	public static String getRootParamKey(Param param) {
		if (param == null) {
			return null;
		}
		ParamType paramType = ParamType.typeOf(param.getParamType());
		if (paramType == null) {
			return null;
		}
		switch (paramType) {
			case REQUEST:
				return ROOT_REQUEST_PARAM_KEY;
			case RESPONSE:
				return ROOT_RESPONSE_PARAM_KEY;
			default:
				return null;
		}
	}

	private static final String PLATFORM = "UIP";
	private static final String PROJECT = "DOC";

	private static final String POOL = "POOL";
	private static final String ROOT = "ROOT";
	private static final String PUBLIC = "PUBLIC";

	private static final String INTER = "INTER";
	private static final String PARAM = "PARAM";
	private static final String ITEM_RELAT = "ITEM:RELAT";

	private static final String MAPPING = "MAPPING";

	public static final String ROOT_REQUEST_PARAM_KEY = wrapAll(join(PARAM, ROOT, ParamType.REQUEST));
	public static final String ROOT_RESPONSE_PARAM_KEY = wrapAll(join(PARAM, ROOT, ParamType.RESPONSE));
	public static final String PUBLIC_REQUEST_PARAM_POOL_KEY = wrapAll(join(PARAM, PUBLIC, ParamType.REQUEST, POOL));
	public static final String PUBLIC_RESPONSE_PARAM_POOL_KEY = wrapAll(join(PARAM, PUBLIC, ParamType.RESPONSE, POOL));

	public static final String INTER_POOL_KEY = wrapAll(join(INTER, POOL));
	public static final String PARAM_POOL_KEY = wrapAll(join(PARAM, POOL));
	public static final String ITEM_RELAT_POOL_KEY = wrapAll(join(ITEM_RELAT, POOL));

	private static String wrapAll(String key) {
		return (new StringBuilder(PLATFORM)).append(COLON).append(PROJECT).append(COLON).append(key).toString();
	}

	private static String join(Object... keys) {
		StringBuilder sb = new StringBuilder();
		for (Object key : keys) {
			sb.append(key.toString()).append(COLON);
		}
		if (sb.length() > 0 && (sb.lastIndexOf(COLON) == sb.length() - 1)) {
			return sb.substring(0, sb.length() - 1);
		} else {
			return sb.toString();
		}
	}

	public enum ParamType {

		REQUEST("0"), RESPONSE("1");

		private String value;

		private ParamType(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return (new StringBuilder(super.toString())).append(COLON).append(this.value).toString();
		}

		public static ParamType typeOf(String paramType) {
			if (REQUEST.value.equals(paramType)) {
				return REQUEST;
			} else if (RESPONSE.value.equals(paramType)) {
				return RESPONSE;
			} else {
				return null;
			}
		}

	}

}
