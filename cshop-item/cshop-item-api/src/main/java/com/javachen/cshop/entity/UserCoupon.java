package com.javachen.cshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 用户持有优惠券的使用情况表
 *
 * @author june
 * @createTime 2019-06-22 21:07
 * @see
 * @since
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserCoupon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //用户拥有券的编号，自增
    private Integer status; //是否使用，0、未使用  1、已使用  2、已过期

    private Long couponId;

    private Long userId;
}
