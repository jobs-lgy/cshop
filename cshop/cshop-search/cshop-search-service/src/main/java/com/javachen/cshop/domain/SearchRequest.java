package com.javachen.cshop.domain;

import lombok.Data;

import java.util.Map;

@Data
public class SearchRequest {

    /**
     * 搜索条件
     */
    private String key;

    /**
     * 当前页
     */
    private Integer page=1;

    private  Integer size = 20;

    /**
     * 排序字段
     */
    private String sortBy;

    /**
     * 是否降序
     */
    private Boolean descending;

    /**
     * 过滤字段
     */
    private Map<String,String> filter;
}
