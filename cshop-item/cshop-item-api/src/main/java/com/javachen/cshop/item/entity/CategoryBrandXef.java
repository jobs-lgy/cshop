package com.javachen.cshop.item.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
@Table(name = "tb_category_brand")
@Entity
public class CategoryBrandXef implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long categoryId;
    private Long brandId;

    public CategoryBrandXef(Long categoryId, Long brandId) {
        this.categoryId = categoryId;
        this.brandId = brandId;
    }
}
