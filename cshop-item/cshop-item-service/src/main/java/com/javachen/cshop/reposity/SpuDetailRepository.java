package com.javachen.cshop.reposity;

import com.javachen.cshop.item.entity.SpuDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpuDetailRepository extends JpaRepository<SpuDetail, Long> {
    SpuDetail findBySpuId(Long spuId);
}
