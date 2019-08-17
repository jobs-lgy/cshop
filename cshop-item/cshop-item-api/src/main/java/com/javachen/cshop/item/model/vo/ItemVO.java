package com.javachen.cshop.item.model.vo;

import java.math.BigDecimal;

public class ItemVO {
    private Integer id;

    //商品名称
    private String title;

    //商品的描述
    private String description;

    //商品价格
    private BigDecimal price;

    //商品的库存
    private Integer stock;

    //商品的销量
    private Integer sales;

    //商品描述图片的url
    private String imgUrl;

    //记录商品是否在秒杀活动中，以及对应的状态0：表示没有秒杀活动，1表示秒杀活动待开始，2表示秒杀活动进行中
    private Integer promoStatus;

    //秒杀活动价格
    private BigDecimal promoPrice;

    //秒杀活动ID
    private Integer promoId;

    //秒杀活动开始时间
    private String startDate;

}
