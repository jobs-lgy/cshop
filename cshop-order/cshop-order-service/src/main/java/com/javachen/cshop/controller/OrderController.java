package com.javachen.cshop.admin.controller;

import com.javachen.cshop.common.web.advice.BusinessException;
import com.javachen.cshop.common.web.advice.ErrorCode;
import com.javachen.cshop.common.model.response.PageResponse;
import com.javachen.cshop.config.PayHelper;
import com.javachen.cshop.entity.Order;
import com.javachen.cshop.entity.OrderStatus;
import com.javachen.cshop.admin.service.OrderService;
import com.javachen.cshop.admin.service.OrderStatusService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@Api("订单服务接口")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private PayHelper payHelper;

    /**
     * 创建订单
     *
     * @param order 订单对象
     * @return 订单编号
     */
    @PostMapping("/order")
    @ApiOperation(value = "创建订单接口，返回订单编号", notes = "创建订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "order", required = true, value = "订单的json对象，包含订单条目和物流信息"),
    })
    public List<Long> createOrder(@RequestBody @Valid Order order) {
        List<Long> list = this.orderService.findStockId(order);
        if (list.size() != 0) {
            throw new BusinessException(ErrorCode.STOCK_NOT_ENOUGH);
        }

        Long id = this.orderService.add(order);
        return Arrays.asList(id);
    }


    /**
     * 查询订单
     *
     * @param id 订单编号
     * @return 订单对象
     */
    @ApiOperation(value = "根据订单编号查询订单，返回订单对象", notes = "查询订单")
    @ApiImplicitParam(name = "id", required = true, value = "订单编号", type = "Long")
    @GetMapping("/order/{id}")
    public ResponseEntity<Order> queryOrderById(@PathVariable("id") Long id) {
        Order order = this.orderService.findById(id);
        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(order);
    }

    /**
     * 分页查询当前已经登录的用户订单
     *
     * @param page   页数
     * @param rows   每页大小
     * @param status 订单状态
     * @return
     */
    @GetMapping("/order")
    @ApiOperation(value = "分页查询当前用户订单，并且可以根据订单状态过滤", notes = "分页查询当前用户订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", defaultValue = "1", type = "Integer"),
            @ApiImplicitParam(name = "rows", value = "每页大小", defaultValue = "5", type = "Integer"),
            @ApiImplicitParam(name = "status", value = "订单状态：1未付款，" +
                    "2已付款未发货，" +
                    "3已发货未确认，" +
                    "4已确认未评价，" +
                    "5交易关闭，" +
                    "6交易成功，已评价", defaultValue = "1", type = "Integer")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "订单的分页结果"),
            @ApiResponse(code = 404, message = "没有查询到结果"),
            @ApiResponse(code = 500, message = "服务器异常")
    })
    public ResponseEntity<PageResponse<Order>> queryUserOrderList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "status", required = false) Integer status
    ) {

        PageResponse<Order> result = this.orderService.findAllByStatus(page, rows, status);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }


    /**
     * 更新订单状态
     *
     * @param id
     * @param status
     * @return
     */
    @PutMapping("/order/{id}/{status}")
    @ApiOperation(value = "更新订单状态", notes = "更新订单状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单编号", type = "Long"),
            @ApiImplicitParam(name = "status", value = "订单状态：1未付款，" +
                    "2已付款未发货，" +
                    "3已发货未确认，" +
                    "4已确认未评价，" +
                    "5交易关闭，" +
                    "6交易成功，已评价", defaultValue = "1", type = "Integer")
    })
    @ApiResponses({
            @ApiResponse(code = 204, message = "true:修改成功；false:修改状态失败"),
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 500, message = "服务器异常")
    })
    public ResponseEntity<Boolean> updateOrderStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status) {
        Boolean result = this.orderStatusService.updateStatus(id, status);
        if (!result) {
            //返回400
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        //返回204
        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }

    /**
     * 根据订单id生成付款链接
     *
     * @param orderId
     * @return
     */
    @GetMapping("/order/url/{id}")
    @ApiOperation(value = "生成微信扫描支付付款链接", notes = "生成付款链接")
    @ApiImplicitParam(name = "id", value = "订单编号", type = "Long")
    @ApiResponses({
            @ApiResponse(code = 200, message = "根据订单编号生成的微信支付地址"),
            @ApiResponse(code = 404, message = "生成链接失败"),
            @ApiResponse(code = 500, message = "服务器异常")
    })
    public ResponseEntity<String> generateUrl(@PathVariable("id") Long orderId) {
//        String url = this.payHelper.createPayUrl(orderId);
//        if (StringUtils.isNotEmpty(url)){
//            return ResponseEntity.ok(url);
//        }else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
        return ResponseEntity.ok().build();
    }

    /**
     * 查询付款状态
     *
     * @param orderId
     * @return
     */
    @GetMapping("/order/state/{id}")
    @ApiOperation(value = "查询扫码支付的付款状态", notes = "查询付款状态")
    @ApiImplicitParam(name = "id", value = "订单编号", type = "Long")
    @ApiResponses({
            @ApiResponse(code = 200, message = "0, 未查询到支付信息 1,支付成功 2,支付失败"),
            @ApiResponse(code = 500, message = "服务器异常"),
    })
    public ResponseEntity<Integer> queryPayState(@PathVariable("id") Long orderId) {
//        PayState payState = this.payHelper.queryOrder(orderId);
//        return ResponseEntity.ok(payState.getValue());
        return ResponseEntity.ok().build();
    }

    /**
     * 根据订单id查询其包含的skuId
     *
     * @param id
     * @return
     */
    @GetMapping("/order/skuId/{id}")
    @ApiOperation(value = "根据订单号查询其包含的所有商品ID", notes = "查询商品ID")
    @ApiImplicitParam(name = "id", value = "订单编号", type = "Long")
    @ApiResponses({
            @ApiResponse(code = 200, message = "商品订单号集合"),
            @ApiResponse(code = 404, message = "没有找到对应的订单号集合"),
            @ApiResponse(code = 500, message = "服务器异常")
    })
    public ResponseEntity<List<Long>> querySkuIdByOrderId(@PathVariable("id") Long id) {
        List<Long> longList = this.orderService.findSkuIdsByOrderId(id);
        if (longList == null || longList.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(longList);
    }


    /**
     * 根据订单id查询订单状态
     *
     * @param id
     * @return
     */
    @GetMapping("/order/status/{id}")
    @ApiOperation(value = "根据订单号查询订单状态", notes = "查询订单状态")
    @ApiImplicitParam(name = "id", value = "订单编号", type = "Long")
    @ApiResponses({
            @ApiResponse(code = 200, message = "订单状态"),
            @ApiResponse(code = 404, message = "没有找到对应的订单状态"),
            @ApiResponse(code = 500, message = "服务器异常")
    })
    public ResponseEntity<OrderStatus> queryOrderStatusById(@PathVariable("id") Long id) {
        OrderStatus orderStatus = this.orderStatusService.findById(id);
        if (orderStatus == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(orderStatus);
    }
}
