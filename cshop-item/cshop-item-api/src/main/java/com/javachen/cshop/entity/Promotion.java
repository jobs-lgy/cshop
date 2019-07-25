package com.javachen.cshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author june
 * @createTime 2019-06-22 21:03
 * @see
 * @since
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Promotion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;  //促销活动说明
    private Byte type; //活动类型，目前可选的有：0没有促销，1满减，2满额送抵扣券，3秒杀
    //    private Long condition; //满减条件，秒杀的话设置为0
    private Long reducePrice;//满减后的折扣金额
    private BigDecimal seckillPrice; //秒杀价格，如果是秒杀活动，需要填写

    //秒杀活动状态 1表示还未开始，2表示进行中，3表示已结束
    private Byte status;

    private Date startTime; //优惠券生效时间
    private Date endTime; //优惠券失效时间

    private Long skuId;
    private Long couponId;
}
