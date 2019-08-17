package com.javachen.cshop.item.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author june
 * @createTime 2019-06-22 20:58
 * @see
 * @since
 */
@Data
@RequiredArgsConstructor
@Entity
public class Coupon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;  //优惠卷名称
    private Integer type; //优惠卷类型,1、抵扣  2、折扣(打折）
    //    private Long condition; //抵扣或折扣条件，如果没有限制，则设置为0
    private Long reduction;//优惠金额
    private Integer discount; //如果没有折扣，为100。如果是八五折，折扣为85
    private String targets; //优惠券可以生效的sku的id拼接，以,分割
    private Integer stock; //剩余优惠券数量
    private Date startTime; //优惠券生效时间
    private Date endTime; //优惠券失效时间
}