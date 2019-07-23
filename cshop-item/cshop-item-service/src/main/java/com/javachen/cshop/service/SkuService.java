package com.javachen.cshop.service;

import com.javachen.cshop.entity.Sku;

import java.util.List;

/**
 * 商品对外接口
 */
public interface SkuService {
    List<Sku> findAllBySpuId(Long spuId);

    List<Sku> findAll();

    Sku findById(Long id);
}
