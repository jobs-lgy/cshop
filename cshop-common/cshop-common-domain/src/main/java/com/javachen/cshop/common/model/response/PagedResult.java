package com.javachen.cshop.common.model.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PagedResult<T> implements Serializable {

    private Long total;// 总条数
    private Integer pageCount;// 总页数

    private Integer pageSize;

    private Integer currentPage;

    private List<T> list;// 当前页数据

    public PagedResult() {
    }

    public PagedResult(Long total, int currentPage, Integer pageSize, List<T> list) {
        super();
        this.currentPage = currentPage + 1;//当前页码
        this.total = total;//总记录数
        this.pageSize = pageSize;//页码容量
        this.pageCount = (int) Math.ceil(total / this.pageSize) + 1;
        this.list = list;
    }

}