//package com.javachen.listener;
//
//import com.javachen.service.SearchService;
//import org.springframework.amqp.core.ExchangeTypes;
//import org.springframework.amqp.rabbit.annotation.Exchange;
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.QueueBinding;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ItemListener {
//
//    @Autowired
//    private SearchService searchService;
//
//    /**
//     * 处理insert和update的消息
//     * @param id
//     * @throws Exception
//     */
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = "cshop.create.index.queue",durable = "true"), //队列持久化
//            exchange = @Exchange(
//                    value = "cshop.cshop.exchange",
//                    ignoreDeclarationExceptions = "true",
//                    type = ExchangeTypes.TOPIC
//            ),
//            key = {"cshop.insert","cshop.update"}
//    ))
//    public void listenCreate(Long id) throws Exception{
//        if (id == null){
//            return;
//        }
//        //创建或更新索引
//        this.searchService.createIndex(id);
//    }
//
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = "cshop.delete.index.queue",durable = "true"), //队列持久化
//            exchange = @Exchange(
//                    value = "cshop.cshop.exchange",
//                    ignoreDeclarationExceptions = "true",
//                    type = ExchangeTypes.TOPIC
//            ),
//            key = {"cshop.delete"}
//    ))
//    public void listenDelete(Long id){
//        if (id == null){
//            return;
//        }
//
//        //删除索引
//        this.searchService.deleteIndex(id);
//    }
//}
