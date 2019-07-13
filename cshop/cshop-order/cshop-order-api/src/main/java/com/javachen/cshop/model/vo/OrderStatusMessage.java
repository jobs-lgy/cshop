package com.javachen.cshop.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 98050
 * @Time: 2018-12-10 23:27
 * @Feature:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusMessage {
    private Long orderId;

    private Long userId;

    private String username;

    private Long spuId;

    /**
     * 消息类型：1(自动确认收货)  2（自动评论）
     */
    private int type;
}
