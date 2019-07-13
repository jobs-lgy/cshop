package com.javachen.service;

import com.javachen.domain.Item;
import com.javachen.domain.SearchRequest;
import com.javachen.domain.SearchResult;
import com.javachen.model.vo.SpuBo;

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
