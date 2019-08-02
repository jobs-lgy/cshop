package com.javachen.cshop.admin.service;

import com.javachen.cshop.domain.Item;
import com.javachen.cshop.domain.SearchRequest;
import com.javachen.cshop.domain.SearchResult;
import com.javachen.cshop.model.vo.SpuBo;

import java.io.IOException;

public interface SearchService {

    /**
     * 查询商品信息
     *
     * @param spuBo
     * @return
     * @throws IOException
     */
    Item buildItem(SpuBo spuBo) throws IOException;

    /**
     * 商品搜索
     *
     * @param searchRequest
     * @return
     */
    SearchResult<Item> search(SearchRequest searchRequest);


    /**
     * 根据goods的id创建相应的索引
     *
     * @param id
     * @throws IOException
     */
    void createIndex(Long id) throws IOException;

    /**
     * 根据goods的id删除相应的索引
     *
     * @param id
     */
    void deleteIndex(Long id);
}
