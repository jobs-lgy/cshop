package com.javachen.cshop.item.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_stock")
@Entity
public class Stock implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long skuId;
    private Integer seckillStock;// 可秒杀库存
    private Integer seckillTotal;// 秒杀总数量
    private Integer stock;// 正常库存
}