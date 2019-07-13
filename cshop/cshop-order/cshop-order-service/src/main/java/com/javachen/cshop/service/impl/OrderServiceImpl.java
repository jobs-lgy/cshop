package com.javachen.cshop.service.impl;

import com.javachen.cshop.common.auth.AuthUser;
import com.javachen.cshop.common.response.PageResponse;
import com.javachen.cshop.common.util.IdWorker;
import com.javachen.cshop.common.web.LoginInterceptor;
import com.javachen.cshop.entity.Order;
import com.javachen.cshop.entity.OrderDetail;
import com.javachen.cshop.entity.OrderStatus;
import com.javachen.cshop.entity.Stock;
import com.javachen.cshop.feign.SpuClient;
import com.javachen.cshop.repository.OrderDetailRepository;
import com.javachen.cshop.repository.OrderRepository;
import com.javachen.cshop.repository.OrderStatusRepository;
import com.javachen.cshop.repository.StockRepository;
import com.javachen.cshop.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private SpuClient spuClient;

//    @Autowired
//    private RedisTemplate redisTemplate;

//    @Autowired
//    private SeckillOrderRepository seckillOrderRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long add(Order order) {
        //创建订单
        //1.生成orderId
        long orderId = idWorker.nextId();
        //2.获取登录的用户
        AuthUser userResponse = LoginInterceptor.getLoginUser();
        //3.初始化数据
        order.setBuyerName(userResponse.getUsername());
        order.setBuyerRate(false);
        order.setOrderId(orderId);
        order.setUserId(userResponse.getId());
        //4.保存数据
        this.orderRepository.save(order);

        //5.保存订单状态
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        //初始状态未未付款：1
        orderStatus.setStatus(1);
        //6.保存数据
        this.orderStatusRepository.save(orderStatus);

        //7.在订单详情中添加orderId
        order.getOrderDetails().forEach(orderDetail -> {
            //添加订单
            orderDetail.setOrderId(orderId);
        });

        //8.保存订单详情，使用批量插入功能
        this.orderDetailRepository.saveAll(order.getOrderDetails());

        //减库存  FIXME
//        order.getOrderDetails().forEach(orderDetail -> this.stockRepository.reduceStock(orderDetail.getSkuId(), orderDetail.getNum()));

        return orderId;

    }

    /**
     * 根据订单号查询订单
     *
     * @param id
     * @return
     */
    @Override
    public Order findById(Long id) {
        //1.查询订单
        Optional<Order> optional = this.orderRepository.findById(id);
        if (optional.isPresent()) {
            Order order = optional.get();
            //2.查询订单详情
            List<OrderDetail> orderDetail = orderDetailRepository.findAllByOrderId(order.getOrderId());

            //3.查询订单状态
            Optional<OrderStatus> optionalOrderStatus = this.orderStatusRepository.findById(order.getOrderId());
            if (optionalOrderStatus.isPresent()) {
                OrderStatus orderStatus = optionalOrderStatus.get();
                //4.order对象填充订单详情
                order.setOrderDetails(orderDetail);
                //5.order对象设置订单状态
                order.setStatus(orderStatus.getStatus());
                //6.返回order
                return order;
            }

        }
        return null;


    }

    /**
     * 查询当前登录用户的订单，通过订单状态进行筛选
     *
     * @param page
     * @param rows
     * @param status
     * @return
     */
    @Override
    public PageResponse<Order> findAllByStatus(Integer page, Integer rows, Integer status) {
        try {
            AuthUser userResponse = LoginInterceptor.getLoginUser();
            //3.查询
            Page<Order> pageInfo = this.orderRepository.findAllByUserIdAndStatus(userResponse.getId(), status, new PageRequest(page, rows));
            //4.填充orderDetail
            List<Order> orderList = pageInfo.getContent();
            orderList.forEach(order -> {
                List<OrderDetail> orderDetailList = this.orderDetailRepository.findAllByOrderId(order.getOrderId());
                order.setOrderDetails(orderDetailList);
            });
            return new PageResponse<Order>(pageInfo.getTotalElements(), pageInfo.getTotalPages(), pageInfo.getContent());
        } catch (Exception e) {
            log.error("查询订单出错", e);
            return null;
        }
    }

    /**
     * 根据订单号查询商品id
     *
     * @param id
     * @return
     */
    @Override
    public List<Long> findSkuIdsByOrderId(Long id) {
        List<OrderDetail> orderDetailList = this.orderDetailRepository.findAllByOrderId(id);
        List<Long> ids = new ArrayList<>();
        orderDetailList.forEach(orderDetail -> ids.add(orderDetail.getSkuId()));
        return ids;
    }

    /**
     * 查询订单下商品的库存，返回库存不足的商品Id
     *
     * @param order
     * @return
     */
    @Override
    public List<Long> findStockId(Order order) {
        List<Long> skuId = new ArrayList<>();
        order.getOrderDetails().forEach(orderDetail -> {
            Optional<Stock> stockOptional = this.stockRepository.findById(orderDetail.getSkuId());
            if (stockOptional.isPresent()) {
                if (stockOptional.get().getStock() - orderDetail.getNum() < 0) {
                    //先判断库存是否充足
                    skuId.add(orderDetail.getSkuId());
                }
            }

        });
        return skuId;
    }

    /**
     * 根据订单id查询其skuId
     *
     * @param id
     * @return
     */
    public Long findSkuIdByOrderId(Long id) {
        List<OrderDetail> orderDetail = this.orderDetailRepository.findAllByOrderId(id);
        return orderDetail.get(0).getSkuId();
    }
}
