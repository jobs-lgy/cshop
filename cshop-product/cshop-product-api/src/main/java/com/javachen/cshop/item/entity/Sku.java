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
@Table(name = "tb_sku")
@Entity
@DynamicUpdate
public class Sku implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long spuId;
    private String title;
    private String images; //多个图片用逗号分隔
    private Long price;

    private Boolean enable;// 是否有效，逻辑删除用
    @CreationTimestamp
    private Date createTime;// 创建时间
    @UpdateTimestamp
    private Date updateTime;// 最后修改时间
}