package com.javachen.cshop.item.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_spu")
@DynamicUpdate
public class Spu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long brandId;
    private Long categoryId;
    private String title;// 标题
    private String subTitle;// 子标题
    private int status;

    private String description;// 商品描述
    private String packingList;// 包装清单
    private String afterService;// 售后服务

    private Boolean enable;// 是否有效，逻辑删除用
    @CreationTimestamp
    private Date createTime;// 创建时间
    @UpdateTimestamp
    private Date updateTime;// 最后修改时间
}