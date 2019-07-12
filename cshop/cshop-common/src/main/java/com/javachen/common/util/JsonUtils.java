package com.javachen.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

public class JsonUtils {
    private static final String FILTER_NAME = "_Filter_Name";
    private static final String JSON_DATE_FROMATE = "yyyy-MM-dd HH:mm:ss";
    private static ObjectMapper objectMapper;

    static{
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); //不输出value=null的属性
        objectMapper.setDateFormat(new SimpleDateFormat(JSON_DATE_FROMATE));
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    /**
     * deserializea a json string to an object
     *
     * @param <T>
     * @param jsonAsString
     * @param pojoClass
     * @return
     * @throws Exception
     */
    public static <T> T fromJson(String jsonAsString, Class<T> pojoClass){
        try {
            return (T) getObjectMapper().readValue(jsonAsString, pojoClass);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * deserializea a json string to an object
     *
     * @param <T>
     * @param jsonAsString
     * @return
     * @throws Exception
     */
    public static <T> T fromJson(String jsonAsString, TypeReference<T> type) {
        try {
            return (T) getObjectMapper().readValue(jsonAsString, type);
        } catch (IOException e) {
            return null;
        }
    }

    private static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static String toJson(Object pojo, boolean prettyPrint,
                                String... filterNames) throws IOException {
        return toJson(pojo, false,getObjectMapper(), filterNames);
    }

    public static String toJsonFull(Object pojo, boolean prettyPrint,
                                String... filterNames) throws Exception {
        return toJson(pojo, false, getObjectMapper(), filterNames);
    }

    private static String toJson(Object pojo, boolean prettyPrint,ObjectMapper objectMapper,
                                String... filterNames) throws IOException {
        StringWriter sw = new StringWriter();
        JsonFactory jsonFactory = new JsonFactory();
        JsonGenerator jg = jsonFactory.createGenerator(sw);
        if (prettyPrint) {
            jg.useDefaultPrettyPrinter();
        }
        SimpleFilterProvider fp = new SimpleFilterProvider();
        if (filterNames != null && filterNames.length > 0) {
            fp.addFilter(
                    FILTER_NAME,
                    SimpleBeanPropertyFilter.serializeAllExcept(filterNames));
        } else {
            fp.addFilter(
                    FILTER_NAME,
                    SimpleBeanPropertyFilter.serializeAllExcept(new String[]{}));
        }
        objectMapper.writer(fp).writeValue(jg, pojo);
        return sw.toString();
    }


    public static String toJson(Object pojo, String... filterNames)
            throws Exception {
        return toJson(pojo, false, filterNames);
    }

    public static String toJson(Object pojo) throws IOException {
        return toJson(pojo, false);
    }

}
