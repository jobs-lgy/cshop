package com.javachen.cshop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author june
 * @createTime 2019-06-21 16:21
 * @see
 * @since
 */
@Data
@NoArgsConstructor
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;//名称
    private String pinyin;
    private String abbr;//简称
    private Boolean enable;//状态

    private Integer priority;

    private Long provinceId;
}
