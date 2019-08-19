package com.javachen.cshop.item.model.vo;

import com.javachen.cshop.item.entity.Sku;
import com.javachen.cshop.item.entity.Spu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SpuBo extends Spu {
    // 分类名称
    @Transient
    private String categoryName;

    // 品牌名称
    @Transient
    private String brandName;

    // 商品列表
    @Transient
    private List<Sku> skuList;
}