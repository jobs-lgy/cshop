package com.javachen.cshop.email.config;

import com.javachen.cshop.common.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dm
 */
@Configuration
public class RabbitMQUtils {

    private Map<String, Object> args = new HashMap<>();

    @PostConstruct
    public void init(){
        // 设置该Queue的死信的信箱
        args.put("x-dead-letter-exchange", Constants.DEAD_LETTER_EXCHANGE);
        // 设置死信routingKey
        args.put("x-dead-letter-routing-key", Constants.DEAD_LETTER_ROUTINKEY);
    }

    //声明一个死信交换机
    @Bean
    public TopicExchange deadLetterExchange() {
        return new TopicExchange(
                Constants.DEAD_LETTER_EXCHANGE,
                true,
                true);
    }

    //声明一个死信队列用来存放死信消息
    @Bean
    public Queue deadQueue() {
        return new Queue(Constants.DEAD_QUEUE,
                true,
                false,
                true,
                null);
    }

    // 将死信队列和死信的交换机绑定
    @Bean
    public Binding bindingDead() {
        return BindingBuilder.bind(deadQueue()).
                to(deadLetterExchange()).
                with(Constants.DEAD_LETTER_ROUTINKEY);
    }

    @Bean
    public Queue toCreateOrderQueue() {
        return new Queue(Constants.RabbitQueueName.TO_CREATE_ORDER, true, false, true, args);
    }

    @Bean
    public Queue toUpdateOrderQueue() {
        return new Queue(Constants.RabbitQueueName.TO_UPDATED_ORDER_QUEUE, true, false, true, args);
    }

    @Bean
    public Queue toUpdateGoodsQueue() {
        return new Queue(Constants.RabbitQueueName.TO_UPDATED_GOODS_QUQUE, true, false, true, args);
    }

    /**
     * 重置座位状态
     *
     * @return
     */
    @Bean
    public Queue toResetSeatQueue() {
        args.put("x-dead-letter-exchange", Constants.DEAD_LETTER_EXCHANGE);
        // 设置死信routingKey
        args.put("x-dead-letter-routing-key", Constants.DEAD_LETTER_ROUTINKEY);
        return new Queue(Constants.RabbitQueueName.TO_RESET_SEAT_QUQUE, true, false, true, args);
    }

    /**
     * 删除订单
     *
     * @return
     */
    @Bean
    public Queue toDelOrderQueue() {
        return new Queue(Constants.RabbitQueueName.TO_DEL_ORDER_QUQUE, true, false, true, args);
    }

    /**
     * 重置联系人
     *
     * @return
     */
    @Bean
    public Queue toResetLinkUserQueue() {
        return new Queue(Constants.RabbitQueueName.TO_RESET_LINKUSER_QUQUE, true, false, true, args);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(
                Constants.RabbitQueueName.TOPIC_EXCHANGE,
                true,
                true);
    }


    @Bean
    public Binding bindingToCreateOrder() {
        return BindingBuilder.bind(toCreateOrderQueue()).to(topicExchange()).with("key.toCreateOrder");
    }

    @Bean
    public Binding bindingToUpdateOrder() {
        return BindingBuilder.bind(toUpdateOrderQueue()).to(topicExchange()).with("key.toUpdateOrder");
    }

    @Bean
    public Binding bindingToUpdateGoods() {
        return BindingBuilder.bind(toUpdateGoodsQueue()).to(topicExchange()).with("key.toUpdateGoods");
    }

    @Bean
    public Binding bindingToResetSeat() {
        return BindingBuilder.bind(toResetSeatQueue()).to(topicExchange()).with(Constants.RabbitQueueName.TO_RESET_SEAT_QUQUE);
    }

    @Bean
    public Binding bindingToDelOrder() {
        return BindingBuilder.bind(toDelOrderQueue()).to(topicExchange()).with(Constants.RabbitQueueName.TO_DEL_ORDER_QUQUE);
    }

    @Bean
    public Binding bindingToResetLinkUser() {
        return BindingBuilder.bind(toResetLinkUserQueue()).to(topicExchange()).with(Constants.RabbitQueueName.TO_RESET_LINKUSER_QUQUE);
    }
}
