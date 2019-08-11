package com.javachen.cshop.common.domain.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResponse<T> implements Serializable {

    private Long total;// 总条数
    private Integer totalPage;// 总页数
    private List<T> list;// 当前页数据

    public PageResponse() {
    }

    public PageResponse(Long total, Integer totalPage, List<T> list) {
        this.total = total;
        this.totalPage = totalPage;
        this.list = list;
    }

    public PageResponse(Long total, List<T> list) {
        this(total, 10, list);
    }

}