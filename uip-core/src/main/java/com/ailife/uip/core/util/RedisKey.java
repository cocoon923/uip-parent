package com.ailife.uip.core.util;

import com.ailife.uip.core.entity.Inter;
import com.ailife.uip.core.entity.JsonBean;
import com.ailife.uip.core.entity.Param;

import static com.ailife.uip.core.util.Symbol.COLON;

/**
 * Created by chenmm6 on 2014/11/6.
 */
public class RedisKey {

    private static final String PLATFORM = "UIP";
    private static final String PROJECT = "DOC";
    private static final String ID_GENERATOR = "ID:GENERATOR";
    public static final String ID_GENERATOR_KEY = wrapAll(ID_GENERATOR);
    private static final String POOL = "POOL";
    private static final String ROOT = "ROOT";
    private static final String PUBLIC = "PUBLIC";
    private static final String JSONBEAN = "JSONBEAN";
    private static final String INTER = "INTER";
    public static final String INTER_POOL_KEY = wrapAll(join(INTER, POOL));
    private static final String PARAM = "PARAM";
    public static final String ROOT_REQUEST_PARAM_KEY = wrapAll(join(PARAM, ROOT, ParamType.REQUEST));
    public static final String ROOT_RESPONSE_PARAM_KEY = wrapAll(join(PARAM, ROOT, ParamType.RESPONSE));
    public static final String PUBLIC_REQUEST_PARAM_POOL_KEY = wrapAll(join(PARAM, PUBLIC, ParamType.REQUEST, POOL));
    public static final String PUBLIC_RESPONSE_PARAM_POOL_KEY = wrapAll(join(PARAM, PUBLIC, ParamType.RESPONSE, POOL));
    public static final String PARAM_POOL_KEY = wrapAll(join(PARAM, POOL));
    private static final String ITEM_RELAT = "ITEM:RELAT";
    public static final String ITEM_RELAT_POOL_KEY = wrapAll(join(ITEM_RELAT, POOL));
    private static final String MAPPING = "MAPPING";

    public static String getDetailKey(JsonBean jsonBean) {
        if (jsonBean instanceof Inter) {
            return wrapAll(join(INTER, jsonBean.getSeq()));
        } else if (jsonBean instanceof Param) {
            return wrapAll(join(PARAM, jsonBean.getSeq()));
        } else {
            return null;
        }
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

        public static ParamType typeOf(String paramType) {
            if (REQUEST.value.equals(paramType)) {
                return REQUEST;
            } else if (RESPONSE.value.equals(paramType)) {
                return RESPONSE;
            } else {
                return null;
            }
        }

        @Override
        public String toString() {
            return (new StringBuilder(super.toString())).append(COLON).append(this.value).toString();
        }

    }

}
