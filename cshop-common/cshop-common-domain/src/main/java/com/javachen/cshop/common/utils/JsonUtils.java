package com.javachen.cshop.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

import static java.time.ZoneId.of;
import static java.util.TimeZone.getTimeZone;

public class JsonUtils {
    private static final String JSON_DATE_FROMATE = "yyyy-MM-dd HH:mm:ss";

    public static <T> T fromJson(String jsonAsString, Class<T> pojoClass) {
        try {
            return (T) getObjectMapper().readValue(jsonAsString, pojoClass);
        } catch (IOException e) {
            throw new RuntimeException("解析json出错", e);
        }
    }

    public static <T> T fromJson(String jsonAsString, TypeReference<T> type) {
        try {
            return (T) getObjectMapper().readValue(jsonAsString, type);
        } catch (IOException e) {
            throw new RuntimeException("解析json出错", e);
        }
    }

    public static String toJson(Object pojo, boolean prettyPrint) {
        ObjectMapper objectMapper = getObjectMapper();
        StringWriter sw = new StringWriter();
        JsonFactory jsonFactory = new JsonFactory();
        try {
            JsonGenerator jg = jsonFactory.createGenerator(sw);
            if (prettyPrint) {
                jg.useDefaultPrettyPrinter();
            }

            objectMapper.writeValue(jg, pojo);
        } catch (IOException e) {
            throw new RuntimeException("解析json出错", e);
        }
        return sw.toString();
    }

    public static String toJson(Object pojo) {
        return toJson(pojo, false);
    }

    private static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setDateFormat(new SimpleDateFormat(JSON_DATE_FROMATE))
                .setTimeZone(getTimeZone(of("Asia/Shanghai")));
        return objectMapper;
    }
}
