package com.javachen.cshop.service.impl;

import com.javachen.cshop.item.entity.SpuDetail;
import com.javachen.cshop.reposity.SpuDetailRepository;
import com.javachen.cshop.service.SpuDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SpuDetailServiceImpl implements SpuDetailService {

    @Autowired
    private SpuDetailRepository spuDetailRepository;

    @Override
    public SpuDetail findBySpuId(Long spuId) {
        return spuDetailRepository.findBySpuId(spuId);
    }
}
