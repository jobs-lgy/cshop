package com.javachen.cshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author june
 * @createTime 2019-06-22 00:08
 * @see
 * @since
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Permission {
    protected String name;
    protected String description;
    protected String type;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
