package com.javachen.cshop.service;

import com.javachen.cshop.item.entity.SpuDetail;

/**
 * 商品对外接口
 */
public interface SpuDetailService {
    SpuDetail findBySpuId(Long spuId);
}
