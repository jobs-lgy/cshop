package com.javachen.cshop.service;

import com.javachen.cshop.entity.OrderStatus;

public interface OrderStatusService {
//    /**
//     * 发送消息到延时队列
//     * @param orderStatusMessage
//     */
//    void sendMessage(OrderStatusMessage orderStatusMessage);

//    /**
//     * 发送评论信息
//     * @param commentsParameter
//     */
//    void sendComments(CommentsParameter commentsParameter);

    public Boolean updateStatus(Long id, Integer status);

    /**
     * 根据订单号查询订单状态
     * @param id
     * @return
     */
    OrderStatus findById(Long id);
}
