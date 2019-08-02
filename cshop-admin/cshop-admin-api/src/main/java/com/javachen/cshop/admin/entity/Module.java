package com.javachen.cshop.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author june
 * @createTime 2019-08-02 14:14
 * @see
 * @since
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String label;

    private String icon;

    private int sort;

}
