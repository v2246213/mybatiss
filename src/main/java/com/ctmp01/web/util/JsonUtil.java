package com.ctmp01.web.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by Administrator on 2017/5/31.
 */
public class JsonUtil {
    private static final ObjectMapper OBJECT_MAPPER;
    static {
        OBJECT_MAPPER = new ObjectMapper();
        // 去除默认的时间格式
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                false);
        // 设置时区为中国上海
        OBJECT_MAPPER.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_NULL_MAP_VALUES,
                false);
        // 序列化时 NULL 的处理
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        // 反序列化时，属性不存在的兼容处理
        OBJECT_MAPPER.getDeserializationConfig().withoutFeatures(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 序列化时，统一的时间格式处理
        OBJECT_MAPPER
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        OBJECT_MAPPER
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        OBJECT_MAPPER.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 单引号的处理
        OBJECT_MAPPER
                .configure(
                        com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES,
                        true);
    }

    /**
     * String to Generic
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            if (StringUtils.isNotEmpty(json))
                return OBJECT_MAPPER.readValue(json, clazz);
        } catch (JsonParseException e) {
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
        }
        return null;
    }

    public static <T> T toObject(Object object, Class<T> clazz) {
        return toObject(JsonUtil.toJson(object), clazz);
    }

    /**
     * String to Map
     *
     * @param json
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMap(String json) {
        return toObject(json, HashMap.class);
    }

    /**
     * Generic to String
     *
     * @param src
     * @return
     */
    public static <T> String toJson(T src) {
        try {
            if (src instanceof String) {
                return (String) src;
            } else {
                return OBJECT_MAPPER.writeValueAsString(src);
            }
        } catch (JsonGenerationException e) {
        } catch (JsonMappingException e) {
        } catch (IOException e) {
        }
        return null;
    }

    /**
     * Json to Collection
     *
     * @param json
     * @param typeReference
     * @return
     */
    public static <T> T toCollection(String json, TypeReference<T> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(json, typeReference);
        } catch (JsonParseException e) {
        } catch (JsonMappingException e) {
        } catch (IOException e) {
        }
        return null;
    }
    public static void main(String[] args) {
        String json = "{'w':'90','h':'30'}";
        Map<String, Object> map = JsonUtil.toMap(json);
        System.out.println(map);
    }
}
