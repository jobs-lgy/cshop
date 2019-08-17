package com.javachen.cshop.item.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author june
 * @createTime 2019-06-17 23:30
 * @see
 * @since
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CategoryBrand.CategoryBrandPk.class)
@Table(name = "tb_category_brand")
@Entity
public class CategoryBrand implements Serializable {
    @Id
    private Long categoryId;

    @Id
    private Long brandId;

    /**
     * @author june
     * @createTime 2019-06-18 00:30
     * @see
     * @since
     */
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class CategoryBrandPk implements Serializable {
        private Long categoryId;
        private Long brandId;
    }
}
