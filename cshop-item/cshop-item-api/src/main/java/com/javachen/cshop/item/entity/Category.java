package com.javachen.cshop.item.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author june
 * @createTime 2019-06-17 19:55
 * @see
 * @since
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_category")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //父类目id，顶级类目填0
    private Long parentId;

    //是否为父节点，0为否，1为是
    private Boolean isParent;

    //排序指数，越小越靠前
    private Integer sort;
}