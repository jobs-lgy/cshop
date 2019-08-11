
package com.javachen.cshop.common.domain;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {
    //当前页数
    private Integer curPage;
    /**
     * 总记录数 .
     */
    private Integer total;
    /**
     * 每页行数 .
     */
    private Integer pageSize;
    /**
     * 页面的总数  .
     */
    private Integer pageCount;
    /**
     * 结果集中数据的起始位置  .
     */
    private Integer beginPos;
    /**
     * List 集合.
     */
    private List<T> list;

    public Page() {

    }
    /**
     * 当前页面 .
     * 页面的大小 .
     * @param curpage .
     * @param pageSize .
     */
    public Page(int curpage, Integer pageSize) {
        this.curPage = curpage;
        this.pageSize = pageSize;
    }
    /**
     * @param curpage .
     * @param total .
     * @param pageSize .
     */
    public Page(int curpage, Integer pageSize, Integer total) {
        super();
        this.curPage = curpage;//当前页码
        this.total = total;//总记录数
        this.pageSize = pageSize;//页码容量
        //总页数=总记录数total/pageSize（+1）
        this.pageCount = (total + this.pageSize - 1) /this.pageSize;
        //下标起始位置：(curPage-1)*pageSize
        this.beginPos = (curPage-1)*pageSize;
    }

}
