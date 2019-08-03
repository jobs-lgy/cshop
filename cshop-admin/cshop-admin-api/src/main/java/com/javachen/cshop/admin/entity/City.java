package com.javachen.cshop.admin.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author june
 * @createTime 2019-06-21 16:21
 * @see
 * @since
 */
@Data
@NoArgsConstructor
@Entity
public class City implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;//名称
    private String abbr;//简称
    private int status;
    private int sort;

    private Long provinceId;
}
