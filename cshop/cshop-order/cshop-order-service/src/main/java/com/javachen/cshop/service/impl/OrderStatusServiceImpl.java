package com.javachen.cshop.service.impl;

import com.javachen.cshop.entity.OrderStatus;
import com.javachen.cshop.repository.OrderStatusRepository;
import com.javachen.cshop.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {
    @Autowired
    private OrderStatusRepository orderStatusRepository;

//    @Autowired
////    private AmqpTemplate amqpTemplate;
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(OrderStatusServiceImpl.class);
//
//
//    /**
//     * 发送延时消息到延时队列中
//     * @param orderStatusMessage
//     */
//    @Override
//    public void sendMessage(OrderStatusMessage orderStatusMessage) {
//        String json = JsonUtils.serialize(orderStatusMessage);
//        MessageProperties properties;
//        if (orderStatusMessage.getType() == 1){
//            // 持久性 non-persistent (1) or persistent (2)
//            properties = MessagePropertiesBuilder.newInstance().setExpiration("60000").setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
//        }else {
//            properties = MessagePropertiesBuilder.newInstance().setExpiration("90000").setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
//        }
//
//        Message message = MessageBuilder.withBody(json.getBytes()).andProperties(properties).build();
//        //发送消息
//        try {
//            this.amqpTemplate.convertAndSend("", "leyou.order.delay.queue", message);
//        }catch (Exception e){
//            LOGGER.error("延时消息发送异常，订单号为：id：{}，用户id为：{}",orderStatusMessage.getOrderId(),orderStatusMessage.getUserId(),e);
//        }
//    }
//
//    /**
//     * 将评论发送到消息队列中
//     * @param commentsParameter
//     */
//    @Override
//    public void sendComments(CommentsParameter commentsParameter) {
//        String json = JsonUtils.serialize(commentsParameter);
//        try {
//            this.amqpTemplate.convertAndSend("leyou.comments.exchange","user.comments", json);
//        }catch (Exception e){
//            LOGGER.error("评论消息发送异常，订单id：{}",commentsParameter.getOrderId(),e);
//        }
//    }

    /**
     * 根据订单号查询订单状态
     *
     * @param id
     * @return
     */
    @Override
    public OrderStatus findById(Long id) {
        Optional<OrderStatus> optional = orderStatusRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    /**
     * 更新订单状态
     * @param id
     * @param status
     * @return
     */
    @Override
    public Boolean updateStatus(Long id, Integer status) {
//        AuthUser userInfo = LoginInterceptor.getLoginUser();
//        Long spuId = this.goodsClient.querySkuById(findSkuIdByOrderId(id)).getSpuId();
//
//        OrderStatus orderStatus = new OrderStatus();
//        orderStatus.setOrderId(id);
//        orderStatus.setStatus(status);
//
//        //延时消息
//        OrderStatusMessage orderStatusMessage = new OrderStatusMessage(id,userInfo.getId(),userInfo.getUsername(),spuId,1);
//        OrderStatusMessage orderStatusMessage2 = new OrderStatusMessage(id,userInfo.getId(),userInfo.getUsername(),spuId,2);
//        //1.根据状态判断要修改的时间
//        switch (status){
//            case 2:
//                //2.付款时间
//                orderStatus.setPaymentTime(new Date());
//                break;
//            case 3:
//                //3.发货时间
//                orderStatus.setConsignTime(new Date());
//                //发送消息到延迟队列，防止用户忘记确认收货
//                orderStatusService.sendMessage(orderStatusMessage);
//                orderStatusService.sendMessage(orderStatusMessage2);
//                break;
//            case 4:
//                //4.确认收货，订单结束
//                orderStatus.setEndTime(new Date());
//                orderStatusService.sendMessage(orderStatusMessage2);
//                break;
//            case 5:
//                //5.交易失败，订单关闭
//                orderStatus.setCloseTime(new Date());
//                break;
//            case 6:
//                //6.评价时间
//                orderStatus.setCommentTime(new Date());
//                break;
//
//            default:
//                return null;
//        }
//        int count = this.orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
//        return count == 1;
        return true;
    }

}
