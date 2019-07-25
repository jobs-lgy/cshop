package com.javachen.cshop.common.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.StringWriter;
import java.text.SimpleDateFormat;

import static java.time.ZoneId.of;
import static java.util.TimeZone.getTimeZone;

public class PojoMapper {
    private static final String JSON_DATE_FROMATE = "yyyy-MM-dd HH:mm:ss";

    public static <T> T fromJson(String jsonAsString, Class<T> pojoClass)
            throws Exception {
        return (T) getObjectMapper().readValue(jsonAsString, pojoClass);
    }

    public static <T> T fromJson(String jsonAsString, TypeReference<T> type)
            throws Exception {
        return (T) getObjectMapper().readValue(jsonAsString, type);
    }

    public static String toJson(Object pojo, boolean prettyPrint) throws Exception {
        ObjectMapper objectMapper = getObjectMapper();
        StringWriter sw = new StringWriter();
        JsonFactory jsonFactory = new JsonFactory();
        JsonGenerator jg = jsonFactory.createJsonGenerator(sw);
        if (prettyPrint) {
            jg.useDefaultPrettyPrinter();
        }

        objectMapper.writeValue(jg, pojo);
        return sw.toString();
    }

    public static String toJson(Object pojo)
            throws Exception {
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
