package com.javachen.cshop.item.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author june
 * @createTime 2019-06-17 23:30
 * @see
 * @since
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_spu_spec")
@Entity
public class SpuSpecXef implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long spuId;
    private Long specId;
}
